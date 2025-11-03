package theaugment.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
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

        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.XLONG, false);

        for(int i = 0; i < temp; ++i) {
            AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (!m.isDying && m.currentHealth > 0 && !m.isEscaping) {
                if (playedMusic) {
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, this.attackEffect, true));
                } else {
                    playedMusic = true;
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, this.attackEffect));
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
            AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (!m.isDeadOrEscaped()) {
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
