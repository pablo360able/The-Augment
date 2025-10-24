package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class QuickeningAction extends AbstractGameAction {
    private final int energyGain;

    public QuickeningAction(int energyGain) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.energyGain = energyGain;
    }

    public void update() {
        int skills = 0;
        for(AbstractCard c : DrawCardAction.drawnCards) {
            if (c.type == AbstractCard.CardType.SKILL) {
                skills++;
            }
        }
        if (skills >= 2) {
            AbstractDungeon.actionManager.addToTop(new GainEnergyAction(this.energyGain));
        }

        this.isDone = true;
    }
}