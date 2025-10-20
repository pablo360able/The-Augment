package theaugment.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MultiattackAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public MultiattackAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : p.hand.group) {
                if (c.type == AbstractCard.CardType.ATTACK && c.costForTurn > 0) {
                    c.costForTurn = 0;
                    c.isCostModified = true;
                    c.superFlash(Color.GOLD.cpy());
                }
            }
        }

        this.tickDuration();
    }
}
