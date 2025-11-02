package theaugment.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class EarthquakeAction extends AbstractGameAction {
    public int[] damage;
    private boolean firstFrame;

    public EarthquakeAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type) {
        this.firstFrame = true;
        this.source = source;
        this.damage = amount;
        this.actionType = ActionType.DAMAGE;
        this.damageType = type;
        this.attackEffect = AttackEffect.SMASH;
        this.duration = Settings.ACTION_DUR_FAST;

    }


    public void update() {
    if (this.firstFrame) {
        boolean playedMusic = false;
        int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        for(int i = 0; i < temp; ++i) {
            if (!AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDying && AbstractDungeon.getCurrRoom().monsters.monsters.get(i).currentHealth > 0 && !AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isEscaping) {
                if (playedMusic) {
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hb.cX, AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hb.cY, this.attackEffect, true));
                } else {
                    playedMusic = true;
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hb.cX, AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hb.cY, this.attackEffect));
                }
            }
        }

        this.firstFrame = false;
    }

    this.tickDuration();
    if (this.isDone) {
        for(AbstractPower p : AbstractDungeon.player.powers) {
            p.onDamageAllEnemies(this.damage);
        }

        int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        for(int i = 0; i < temp; ++i) {
            if (!AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDeadOrEscaped()) {
                if (this.attackEffect == AttackEffect.POISON) {
                    AbstractDungeon.getCurrRoom().monsters.monsters.get(i).tint.color.set(Color.CHARTREUSE);
                    AbstractDungeon.getCurrRoom().monsters.monsters.get(i).tint.changeColor(Color.WHITE.cpy());
                } else if (this.attackEffect == AttackEffect.FIRE) {
                    AbstractDungeon.getCurrRoom().monsters.monsters.get(i).tint.color.set(Color.RED);
                    AbstractDungeon.getCurrRoom().monsters.monsters.get(i).tint.changeColor(Color.WHITE.cpy());
                }

                AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                this.damage[i] += Math.min(this.damage[i], m.currentBlock);
                m.damage(new DamageInfo(this.source, this.damage[i], this.damageType));
            }
        }

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }

        if (!Settings.FAST_MODE) {
            this.addToTop(new WaitAction(0.1F));
        }
    }

}
}
