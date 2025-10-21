package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.orbs.Aether;

public class AetherVialAction extends AbstractGameAction {
    public AetherVialAction(int potency) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = potency;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for(int i = 0; i < AbstractDungeon.player.orbs.size(); ++i) {
                for(int j = 0; j < this.amount; ++j) {
                    AbstractDungeon.player.channelOrb(new Aether());
                }
            }

            if (Settings.FAST_MODE) {
                this.isDone = true;
                return;
            }
        }

        this.tickDuration();
    }
}
