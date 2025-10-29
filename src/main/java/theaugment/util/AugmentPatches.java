package theaugment.util;

import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theaugment.cards.CustomTags;
import theaugment.cards.OnAttackedCard;
import theaugment.modifiers.MagicAttack;
import theaugment.powers.*;
import theaugment.relics.DefectiveProsthetic;

import java.util.ArrayList;

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
                if (p instanceof PreDrawPower && !(AbstractDungeon.actionManager.currentAction instanceof DrawCardAction)) {
                    ((PreDrawPower)p).onCardDrawPreDraw(source, amount, endTurnDraw);
                }
            }
        }
    }

    @SpirePatch(
            clz = FastDrawCardAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    AbstractCreature.class,
                    int.class,
                    boolean.class
            }
    )
    public static class OnFastCardDrawPreDraw {
        public static void Prefix (FastDrawCardAction __instance, AbstractCreature source, int amount, boolean endTurnDraw) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof PreDrawPower && !(AbstractDungeon.actionManager.currentAction instanceof FastDrawCardAction)) {
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
            clz = ApplyPowerAction.class,
            method = "update"
    )
    public static class BlockBuffs {
        public static SpireReturn<Void> Prefix (ApplyPowerAction instance, @ByRef float[] ___duration, float ___startingDuration, AbstractPower ___powerToApply) {
            if (instance.target != null && !instance.target.isDeadOrEscaped()) {
                if (___duration[0] == ___startingDuration) {
                    if (instance.target.hasPower(LingeringEntropyPower.POWER_ID)) {
                        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(instance.target, instance.target, LingeringEntropyPower.POWER_ID));
                        String[] buffDebuffs = new String[]{LoseStrengthPower.POWER_ID, LoseDexterityPower.POWER_ID, LoseFocusPower.POWER_ID, LoseArtifactPower.POWER_ID};
                        for (String buffId : buffDebuffs) {
                            if (___powerToApply.ID.equals(buffId)) {
                                ___duration[0] -= Gdx.graphics.getDeltaTime();
                                if (___duration[0] < 0.0F) {
                                    instance.isDone = true;
                                }
                                return SpireReturn.Return();
                            }
                        }
                    } else if (___powerToApply.type == AbstractPower.PowerType.BUFF && instance.target.hasPower(EntropyPower.POWER_ID)) {
                        if (___powerToApply.ID.equals("Artifact")) {
                            int diff = ___powerToApply.amount - instance.target.getPower(EntropyPower.POWER_ID).amount;
                            if (diff > 0) {
                                ___powerToApply.amount = diff;
                                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(instance.target, instance.target, EntropyPower.POWER_ID));
                            } else if (diff < 0) {
                                instance.target.getPower(EntropyPower.POWER_ID).amount = -diff;
                                ___duration[0] -= Gdx.graphics.getDeltaTime();
                                if (___duration[0] < 0.0F) {
                                    instance.isDone = true;
                                }
                                return SpireReturn.Return();
                            } else {
                                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(instance.target, instance.target, EntropyPower.POWER_ID));
                                ___duration[0] -= Gdx.graphics.getDeltaTime();
                                if (___duration[0] < 0.0F) {
                                    instance.isDone = true;
                                }
                                return SpireReturn.Return();
                            }
                        } else {
                            instance.target.getPower(EntropyPower.POWER_ID).amount--;
                            if (instance.target.getPower(EntropyPower.POWER_ID).amount == 0) {
                                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(instance.target, instance.target, EntropyPower.POWER_ID));
                            }
                            if (!AbstractDungeon.actionManager.actions.isEmpty()) {
                                AbstractGameAction next = AbstractDungeon.actionManager.actions.get(0);
                                if (AbstractDungeon.actionManager.actions.get(0) instanceof ApplyPowerAction) {
                                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(instance.target, instance.target, new LingeringEntropyPower(instance.target)));
                                }
                            }
                            ___duration[0] -= Gdx.graphics.getDeltaTime();
                            if (___duration[0] < 0.0F) {
                                instance.isDone = true;
                            }
                            return SpireReturn.Return();
                        }
                    } else if (___powerToApply.ID.equals(EntropyPower.POWER_ID) && instance.target.hasPower("Artifact")) {
                        int diff = ___powerToApply.amount - instance.target.getPower("Artifact").amount;
                        if (diff > 0) {
                            ___powerToApply.amount = diff;
                            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(instance.target, instance.target, "Artifact"));
                        } else if (diff < 0) {
                            instance.target.getPower("Artifact").amount = -diff;
                            ___duration[0] -= Gdx.graphics.getDeltaTime();
                            if (___duration[0] < 0.0F) {
                                instance.isDone = true;
                            }
                            return SpireReturn.Return();
                        } else {
                            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(instance.target, instance.target, "Artifact"));
                            ___duration[0] -= Gdx.graphics.getDeltaTime();
                            if (___duration[0] < 0.0F) {
                                instance.isDone = true;
                            }
                            return SpireReturn.Return();
                        }
                    }
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfTurnCards"
    )
    public static class ResetScriedAway {
        public static void Prefix (AbstractPlayer __instance) {
            Helpers.SCRIED_AWAY_THIS_TURN = 0;
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "onCardDrawOrDiscard"
    )
    public static class IncrementScriedAway {
        public static void Prefix (AbstractPlayer __instance) {
            Helpers.SCRIED_AWAY_THIS_TURN++;
        }
    }

    @SpirePatch(
            clz = MalleablePower.class,
            method = "onAttacked",
            paramtypez = {
                    DamageInfo.class,
                    int.class
            }
    )
    public static class SnipeMalleable {
        public static SpireReturn<Integer> Prefix (MalleablePower __instance, DamageInfo info, int damageAmount) {
            if (AbstractDungeon.player.hasPower(SpellSniperPower.POWER_ID)) {
                for (AbstractDamageModifier mod : DamageModifierManager.getDamageMods(info)) {
                    if (mod instanceof MagicAttack) {
                        return SpireReturn.Return(damageAmount);
                    }
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = ThornsPower.class,
            method = "onAttacked",
            paramtypez = {
                    DamageInfo.class,
                    int.class
            }
    )
    public static class SnipeThorns {
        public static SpireReturn<Integer> Prefix (ThornsPower __instance, DamageInfo info, int damageAmount) {
            if (AbstractDungeon.player.hasPower(SpellSniperPower.POWER_ID)) {
                for (AbstractDamageModifier mod : DamageModifierManager.getDamageMods(info)) {
                    if (mod instanceof MagicAttack) {
                        return SpireReturn.Return(damageAmount);
                    }
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = SharpHidePower.class,
            method = "onUseCard",
            paramtypez = {
                    AbstractCard.class,
                    UseCardAction.class
            }
    )
    public static class SnipeHide {
        public static SpireReturn<Void> Prefix (SharpHidePower __instance, AbstractCard card, UseCardAction action) {
            if (AbstractDungeon.player.hasPower(SpellSniperPower.POWER_ID)) {
                for (AbstractDamageModifier mod : DamageModifierManager.modifiers(card)) {
                    if (mod instanceof MagicAttack) {
                        return SpireReturn.Return();
                    }
                }
            }
            return SpireReturn.Continue();
        }
    }
}

