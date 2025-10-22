package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BackStrikeAction extends AbstractGameAction {
    private AbstractMonster m;

    public BackStrikeAction(AbstractMonster m) {
        this.actionType = ActionType.WAIT;
        this.m = m;
    }

    public void update() {
        if (this.m != null && this.m.hasPower("Frail")) {
            this.addToTop(new GainEnergyAction(1));
            this.addToTop(new DrawCardAction(1));
        }

        this.isDone = true;
    }
}
