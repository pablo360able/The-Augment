package theaugment.util;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theaugment.cards.CustomTags;
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

}
