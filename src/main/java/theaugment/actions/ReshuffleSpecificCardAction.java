package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ReshuffleSpecificCardAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final AbstractCard c;

    public ReshuffleSpecificCardAction(AbstractCard c) {
        this.p = AbstractDungeon.player;
        this.c = c;
        this.setValues(null, source, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            this.isDone = true;
        } else {
            if (this.duration == Settings.ACTION_DUR_FASTER) {
                if (!this.p.discardPile.contains(c)) {
                    this.isDone = true;
                    return;
                }

                this.p.discardPile.removeCard(c);
                this.p.discardPile.moveToDeck(c, true);
            }

            this.tickDuration();
        }

    }
}
