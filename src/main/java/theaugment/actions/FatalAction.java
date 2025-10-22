package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FatalAction extends AbstractGameAction {
    private final AbstractGameAction[] ifFatal;
    private final boolean allowMinions;

    public FatalAction(AbstractCreature target, AbstractCreature source, AbstractGameAction[] ifFatal) {
        this.setValues(target, source);
        this.ifFatal = ifFatal;
        this.allowMinions = false;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    public FatalAction(AbstractCreature target, AbstractCreature source, AbstractGameAction[] ifFatal, boolean allowMinions) {
        this.setValues(target, source);
        this.ifFatal = ifFatal;
        this.allowMinions = allowMinions;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    public FatalAction(AbstractCreature target, AbstractCreature source, AbstractGameAction ifFatal) {
        this.setValues(target, source);
        this.ifFatal = new AbstractGameAction[]{ifFatal};
        this.allowMinions = false;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    public FatalAction(AbstractCreature target, AbstractCreature source, AbstractGameAction ifFatal, boolean allowMinions) {
        this.setValues(target, source);
        this.ifFatal = new AbstractGameAction[]{ifFatal};
        this.allowMinions = allowMinions;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    @Override
    public void update() {
        this.tickDuration();
        if (this.isDone) {
            boolean effective = false;
            if (this.target != null) {
                effective = effectiveOnTarget(this.target);
            } else {
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                    if (effectiveOnTarget(m)) {
                        effective = true;
                    }
                }
            }
            if(effective) {
                for (AbstractGameAction consequence : this.ifFatal) {
                    this.addToBot(consequence);
                }
            }
        }
    }

    private boolean effectiveOnTarget(AbstractCreature m) {
        return (m.isDying || m.currentHealth <= 0) && (this.allowMinions || !m.halfDead && !m.hasPower("Minion"));
    }
}
