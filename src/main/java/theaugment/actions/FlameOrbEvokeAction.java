package theaugment.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class FlameOrbEvokeAction extends AbstractGameAction {
    private DamageInfo[] info;
    private static final float DURATION = 0.1F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;
    private boolean muteSfx = false;
    private final int monstercount;

    public FlameOrbEvokeAction(DamageInfo info, AbstractGameAction.AttackEffect effect) {
        monstercount = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        this.info = new DamageInfo[monstercount];
        for (int i = 0; i < monstercount; i++) {
            this.info[i] = info;
        }
        this.setValues(null, info);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
        this.duration = 0.1F;
    }

    @Override
    public void update() {
        if (!AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            MonsterGroup monsters = AbstractDungeon.getCurrRoom().monsters;
            if (this.duration == 0.1F) {
                for (int i = 0; i < monstercount; i++) {
                    AbstractMonster monster = monsters.monsters.get(i);
                    if (monster.isDeadOrEscaped() || monster.currentHealth <= 0) {
                        break;
                    }
                    this.info[i].output = AbstractOrb.applyLockOn(monster, this.info[i].base);
                    if (this.info[i].type != DamageInfo.DamageType.THORNS && (this.info[i].owner.isDying || this.info[i].owner.halfDead)) {
                        this.isDone = true;
                        return;
                    }

                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(monster.hb.cX, monster.hb.cY, this.attackEffect, this.muteSfx));
                }
            }

            this.tickDuration();
            if (this.isDone) {
                for (int i = 0; i < monstercount; i++) {
                    AbstractMonster monster = monsters.monsters.get(i);
                    if (monster.isDeadOrEscaped() || monster.currentHealth <= 0) {
                        break;
                    }
                    if (this.attackEffect == AttackEffect.POISON) {
                        monster.tint.color = Color.CHARTREUSE.cpy();
                        monster.tint.changeColor(Color.WHITE.cpy());
                    } else if (this.attackEffect == AttackEffect.FIRE) {
                        monster.tint.color = Color.RED.cpy();
                        monster.tint.changeColor(Color.WHITE.cpy());
                    }

                    monster.damage(this.info[i]);
                    if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                        AbstractDungeon.actionManager.clearPostCombatActions();
                    }

                    if (!Settings.FAST_MODE) {
                        this.addToTop(new WaitAction(0.1F));
                    }
                }
            }

        } else {
            this.isDone = true;
        }
    }
}
