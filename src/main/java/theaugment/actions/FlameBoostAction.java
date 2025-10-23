package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import theaugment.orbs.Flame;

public class FlameBoostAction extends AbstractGameAction {
    private static final float DURATION = 0.1F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;
    private final float speedTime;

    public FlameBoostAction() {
        this.target = this.source = AbstractDungeon.player;
        this.actionType = ActionType.SPECIAL;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.speedTime = 0.6F / (float)AbstractDungeon.player.orbs.size();
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            for (AbstractOrb o : AbstractDungeon.player.orbs) {
                if (o instanceof Flame) {
                    AbstractDungeon.actionManager.addToTop(new VFXAction(new OrbFlareEffect(o, OrbFlareEffect.OrbFlareColor.DARK), speedTime));
                    o.evokeAmount += o.passiveAmount;
                }
            }
        }

        this.tickDuration();
    }
}
