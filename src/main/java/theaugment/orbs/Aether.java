package theaugment.orbs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.powers.LoseFocusPower;
import theaugment.util.GeneralUtils;
import theaugment.util.TextureLoader;

import static theaugment.TheAugmentMod.makeID;

public class Aether extends AbstractOrb {
    public static final String ORB_ID = makeID(Aether.class.getSimpleName());
    private static final OrbStrings orbString;
    public static final String[] DESC;
    private float vfxTimer = 1.0F;
    private float vfxIntervalMin = 0.1F;
    private float vfxIntervalMax = 0.4F;
    private static final float ORB_WAVY_DIST = 0.04F;
    private static final float PI_4 = 12.566371F;

    public Aether() {
        this.ID = ORB_ID;
        this.img = TextureLoader.getOrbTexture(GeneralUtils.removePrefix(ORB_ID));
        this.name = orbString.NAME;
        this.baseEvokeAmount = 1;
        this.evokeAmount = this.baseEvokeAmount;
        this.basePassiveAmount = 1;
        this.passiveAmount = this.basePassiveAmount;
        this.updateDescription();
        this.angle = MathUtils.random(360.0F);
        this.channelAnimTimer = 0.5F;
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0] + this.evokeAmount + DESC[1];
    }

    @Override
    public void applyFocus() {

    }

    @Override
    public void onEvoke() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new FocusPower(p, passiveAmount)));
    }

    @Override
    public void onStartOfTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new FocusPower(p, passiveAmount)));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new LoseFocusPower(p, passiveAmount)));
    }

    @Override
    public AbstractOrb makeCopy() {
        return null;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        this.shineColor.a = this.c.a / 2.0F;
        spriteBatch.setColor(this.shineColor);
        spriteBatch.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
        spriteBatch.setBlendFunction(770, 1);
        spriteBatch.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, -this.angle, 0, 0, 96, 96, false, false);
        spriteBatch.setBlendFunction(770, 771);
        this.renderText(spriteBatch);
        this.hb.render(spriteBatch);
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_PLASMA_CHANNEL", 0.1F);
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
        DESC = orbString.DESCRIPTION;
    }
}
