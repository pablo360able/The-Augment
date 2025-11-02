package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theaugment.util.AugmentPatches;

import static theaugment.TheAugmentMod.modID;

public class ReadyAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public ReadyAction(AbstractCreature source, int amount) {
        this.setValues(AbstractDungeon.player, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, true, false, false, true);
            this.addToBot(new WaitAction(0.25F));
            this.tickDuration();
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                for(AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    if (!c.isEthereal) {
                        c.retain = true;
                        c.modifyCostForCombat(-1);
                        if (!AugmentPatches.AugmentCardVars.spontaneous.get(c)) {
                            AugmentPatches.AugmentCardVars.spontaneous.set(c, true);
                            c.rawDescription = modID + ":Spontaneous. NL " + c.rawDescription;
                            c.initializeDescription();
                        }
                    }

                    AbstractDungeon.player.hand.addToTop(c);
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("RetainCardsAction");
        TEXT = uiStrings.TEXT;
    }
}
