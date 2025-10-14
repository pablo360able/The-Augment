package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class BloodAction extends AbstractGameAction {
    private final AbstractGameAction[] ifBlood;
    private final boolean byDamage;

    public BloodAction(AbstractCreature target, AbstractCreature source, AbstractGameAction[] ifBlood) {
        this.setValues(target, source);
        this.ifBlood = ifBlood;
        this.byDamage = false;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    public BloodAction(AbstractCreature target, AbstractCreature source, AbstractGameAction[] ifBlood, boolean byDamage) {
        this.setValues(target, source);
        this.ifBlood = ifBlood;
        this.byDamage = byDamage;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    @Override
    public void update() {
        if (this.shouldCancelAction()) {
            this.isDone = true;
        } else {
            this.tickDuration();
            if (this.isDone) {
                if(target.lastDamageTaken > 0) {
                    for (AbstractGameAction consequence : ifBlood) {
                        if (byDamage) {consequence.amount = target.lastDamageTaken;}
                        this.addToBot(consequence);
                    }
                }
            }
        }
    }
}
