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
        if (p.hand.contains(c)) {
            p.hand.moveToDiscardPile(c);
        } else if (p.drawPile.contains(c)) {
            p.drawPile.moveToDiscardPile(c);
        }
        c.setAdventured(true);
    }
}
