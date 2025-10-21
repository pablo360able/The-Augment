package theaugment.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import theaugment.relics.TrainingWand;

public class CampfireTrainEffect extends AbstractGameEffect {
    private static final float DUR = 2.0F;
    private boolean hasTrained = false;
    private Color screenColor;

    public CampfireTrainEffect() {
        this.screenColor = AbstractDungeon.fadeColor.cpy();
        this.duration = 2.0F;
        this.screenColor.a = 0.0F;
        ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.updateBlackScreenColor();
        if (this.duration < 1.0F && !this.hasTrained) {
            this.hasTrained = true;
            String wandID = TrainingWand.ID;
            if (AbstractDungeon.player.hasRelic(wandID)) {
                AbstractDungeon.player.getRelic(wandID).flash();
                ++AbstractDungeon.player.getRelic(wandID).counter;
                CardCrawlGame.sound.play("CARD_POWER_WOOSH");
                CardCrawlGame.screenShake.shake(ShakeIntensity.HIGH, ShakeDur.SHORT, true);
                CardCrawlGame.metricData.addCampfireChoiceData("TRAIN", Integer.toString(AbstractDungeon.player.getRelic(wandID).counter));
            }

            AbstractDungeon.topLevelEffects.add(new BorderFlashEffect(new Color(0.8F, 0.6F, 0.1F, 0.0F)));
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
            ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
            AbstractDungeon.getCurrRoom().phase = RoomPhase.COMPLETE;
        }

    }

    private void updateBlackScreenColor() {
        if (this.duration > 1.5F) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.5F) * 2.0F);
        } else if (this.duration < 1.0F) {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
        } else {
            this.screenColor.a = 1.0F;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);
    }

    public void dispose() {
    }
}
