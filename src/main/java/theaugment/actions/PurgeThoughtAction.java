package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class PurgeThoughtAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;

    public PurgeThoughtAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else if (this.p.hand.size() == 1) {
                if (this.p.hand.getBottomCard().costForTurn == -1) {
                    this.addToTop(new ApplyPowerAction(p, p, new FocusPower(p, EnergyPanel.getCurrentEnergy())));
                } else if (this.p.hand.getBottomCard().costForTurn > 0) {
                    this.addToTop(new ApplyPowerAction(p, p, new FocusPower(p, this.p.hand.getBottomCard().costForTurn)));
                }

                this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
                this.tickDuration();
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                for(AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    if (c.costForTurn == -1) {
                        this.addToTop(new ApplyPowerAction(p, p, new FocusPower(p, EnergyPanel.getCurrentEnergy())));
                    } else if (c.costForTurn > 0) {
                        this.addToTop(new ApplyPowerAction(p, p, new FocusPower(p, c.costForTurn)));
                    }

                    this.p.hand.moveToExhaustPile(c);
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("RecycleAction");
        TEXT = uiStrings.TEXT;
    }
}
