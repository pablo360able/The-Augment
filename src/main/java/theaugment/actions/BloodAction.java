package theaugment.actions;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.modifiers.MagicAttack;
import theaugment.powers.SpellSniperPower;

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

    public BloodAction(AbstractCreature target, AbstractCreature source, AbstractGameAction ifBlood) {
        this.setValues(target, source);
        this.ifBlood = new AbstractGameAction[]{ifBlood};
        this.byDamage = false;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    public BloodAction(AbstractCreature target, AbstractCreature source, AbstractGameAction ifBlood, boolean byDamage) {
        this.setValues(target, source);
        this.ifBlood = new AbstractGameAction[]{ifBlood};
        this.byDamage = byDamage;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    @Override
    public void update() {
        this.tickDuration();
        if (this.isDone) {
            boolean effective = false;
            if (this.source.hasPower(SpellSniperPower.POWER_ID)) {
                for (AbstractDamageModifier mod : DamageModifierManager.modifiers(AbstractDungeon.player.cardInUse)) {
                    if (mod instanceof MagicAttack) {
                        effective = true;
                        break;
                    }
                }
            } else {
                if (target != null) {
                    effective = target.lastDamageTaken > 0;
                } else {
                    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                        if (m.lastDamageTaken > 0) {
                            effective = true;
                            break;
                        }
                    }
                }
            }
            if(effective) {
                for (AbstractGameAction consequence : ifBlood) {
                    if (byDamage) {consequence.amount = target.lastDamageTaken;}
                    this.addToTop(consequence);
                }
            }
        }
    }
}
