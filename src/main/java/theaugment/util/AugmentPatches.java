package theaugment.util;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theaugment.cards.CustomTags;
import theaugment.cards.OnAttackedCard;
import theaugment.powers.EntropyPower;
import theaugment.powers.PreDrawPower;
import theaugment.relics.DefectiveProsthetic;

public class AugmentPatches {
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "initializeCardPools"
    )
    public static class CardpoolInitFix {
        public static void Postfix(AbstractDungeon __instance) {
            if (AbstractDungeon.player != null) {
                for (AbstractRelic r : AbstractDungeon.player.relics) {
                    if (r instanceof DefectiveProsthetic) {
                        ((DefectiveProsthetic) r).blueCards();
                    }
                }
            }
        }
    }

    @SpirePatch(
            clz = CardGroup.class,
            method = "triggerOnOtherCardPlayed",
            paramtypez = {
                    AbstractCard.class
            }
    )
    public static class DiscardSpontaneous {
        public static void Postfix(CardGroup instance, AbstractCard useCard) {
            for (AbstractCard c : instance.group) {
                if (c != useCard && c.hasTag(CustomTags.SPONTANEOUS)) {
                    AbstractDungeon.actionManager.addToTop(new DiscardSpecificCardAction(c));
                }
            }
        }
    }

    @SpirePatch(
            clz = CardGroup.class,
            method = "initializeDeck",
            paramtypez = {
                    CardGroup.class
            }
    )
    public static class PutAwayAdventitious {
        @SpireInsertPatch (
                rloc = 4,
                localvars={"copy"}
        )
        public static void Insert (CardGroup __instance, CardGroup __deck, CardGroup copy) {
            CardGroup addToDiscard = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            for (AbstractCard c : copy.group) {
                if (c.hasTag(CustomTags.ADVENTITIOUS)) {
                    addToDiscard.addToTop(c);
                }
            }

            for (AbstractCard c : addToDiscard.group) {
                copy.removeCard(c);
            }
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyPreCombatLogic"
    )
    public static class PutAdventitiousInDiscard {
        public static void Prefix (AbstractPlayer instance) {
            for (AbstractCard c : instance.masterDeck.group) {
                if (c.hasTag(CustomTags.ADVENTITIOUS)) {
                    instance.discardPile.addToTop(c);
                }
            }
        }
    }

    @SpirePatch(
            clz = AbstractCreature.class,
            method = "addBlock",
            paramtypez = {
                    int.class
            }
    )
    public static class ApplyFrailToEnemies {
        @SpireInsertPatch (
                rloc = 1,
                localvars={"tmp"}
        )
        public static void Insert (AbstractCreature instance, int blockAmount, @ByRef float[] tmp) {
            if (!instance.isPlayer) {
                for (AbstractPower p : instance.powers) {
                    tmp[0] = p.modifyBlock(tmp[0]);
                }
            }
        }
    }

    @SpirePatch(
            clz = DrawCardAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    AbstractCreature.class,
                    int.class,
                    boolean.class
            }
    )
    public static class OnCardDrawPreDraw {
        public static void Prefix (DrawCardAction __instance, AbstractCreature source, int amount, boolean endTurnDraw) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof PreDrawPower) {
                    ((PreDrawPower)p).onCardDrawPreDraw(source, amount, endTurnDraw);
                }
            }
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "damage",
            paramtypez = {
                    DamageInfo.class
            }
    )
    public static class TriggerOnAttacked {
        public static void Postfix (AbstractPlayer instance, DamageInfo info) {
            for (AbstractCard c : instance.discardPile.group) {
                if (c instanceof OnAttackedCard) {
                    ((OnAttackedCard)c).triggerOnAttacked(info);
                }
            }
        }
    }

    @SpirePatch(
            clz = RemoveDebuffsAction.class,
            method = "update"
    )
    public static class RemoveEntropy {
        public static void Prefix (RemoveDebuffsAction __instance, AbstractCreature ___c) {
            if (___c.hasPower(EntropyPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(___c, ___c, EntropyPower.POWER_ID));
            }
        }
    }

    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = "update"
    )
    public static class BlockBuffs {
        public static SpireReturn<Void> Prefix (ApplyPowerAction instance, float ___duration, float ___startingDuration, AbstractPower ___powerToApply) {
            if (instance.target != null && !instance.target.isDeadOrEscaped()) {
                if (___duration == ___startingDuration) {
                    if (___powerToApply.type == AbstractPower.PowerType.BUFF) {
                        if (instance.target.hasPower(EntropyPower.POWER_ID)) {
                            if (___powerToApply.ID.equals("Artifact")) {
                                int diff = ___powerToApply.amount - instance.target.getPower(EntropyPower.POWER_ID).amount;
                                if (diff > 0) {
                                    ___powerToApply.amount = diff;
                                    AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(instance.target, instance.target, EntropyPower.POWER_ID));
                                } else if (diff < 0) {
                                    instance.target.getPower(EntropyPower.POWER_ID).amount = diff;
                                    return SpireReturn.Return();
                                } else {
                                    AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(instance.target, instance.target, EntropyPower.POWER_ID));
                                    return SpireReturn.Return();
                                }
                            } else if (!___powerToApply.ID.equals(EntropyPower.POWER_ID)) {
                                return SpireReturn.Return();
                            }
                        }
                    }
                }
            }
            return SpireReturn.Continue();
        }
    }
}

