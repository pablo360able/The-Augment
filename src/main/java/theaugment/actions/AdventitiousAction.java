package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.cards.BaseCard;

public class AdventitiousAction extends AbstractGameAction {
    private BaseCard c;

    public AdventitiousAction(BaseCard card) {
        c = card;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        // Due to timing, the card will always be in the draw pile when this triggers
        if (p.drawPile.contains(c)) {
            p.drawPile.moveToDiscardPile(c);
        }
        c.setAdventured(true);
        this.isDone = true;
    }
}
