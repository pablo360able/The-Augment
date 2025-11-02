package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

public class BlockNextTurnAction extends AbstractGameAction {
    private static final float DURATION = 0.1F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;
    private final boolean skipWait;

    public BlockNextTurnAction(AbstractCreature target) {
        this.skipWait = false;
        this.setValues(target, target);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.LIGHTNING;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = 0;
    }

    public void update() {
        this.tickDuration();
        if (this.isDone) {
            this.addToTop(new ApplyPowerAction(this.target, this.target, new NextTurnBlockPower(this.target, this.amount)));
        }
    }
}
