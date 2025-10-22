package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.BloodShotEffect;

public class LoseMaxHpAction extends AbstractGameAction {
    public LoseMaxHpAction(AbstractCreature target, int amount) {
        this.target = target;
        this.amount = amount;
        this.actionType = ActionType.DAMAGE;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            AbstractDungeon.effectList.add(new BloodShotEffect(this.target.hb.cX, this.target.hb.cY, this.target.hb.cX, this.target.hb.cY, this.amount));
            this.target.decreaseMaxHealth(this.amount);
        }

        this.tickDuration();
    }
}
