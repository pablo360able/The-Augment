package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theaugment.cards.CustomTags;

import static theaugment.TheAugmentMod.makeID;

public class EfficiencyAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final AbstractPlayer p;

    public EfficiencyAction(AbstractCreature p, int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, p, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();

                for (AbstractCard c : this.p.hand.group) {
                    c.modifyCostForCombat(-1);
                }

                AbstractDungeon.player.hand.applyPowers();
                this.tickDuration();
                return;
            }


            if (this.amount < 0) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);
                AbstractDungeon.player.hand.applyPowers();
                this.tickDuration();
                return;
            }

            if (this.p.hand.size() > this.amount) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false);
            }

            AbstractDungeon.player.hand.applyPowers();
            this.tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for(AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                c.modifyCostForCombat(-1);
                p.hand.addToTop(c);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID(EfficiencyAction.class.getSimpleName()));
        TEXT = uiStrings.TEXT;
    }
}
