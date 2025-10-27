package theaugment.character;

import basemod.abstracts.CustomEnergyOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;

import static theaugment.TheAugmentMod.characterPath;

public class AugmentOrb extends CustomEnergyOrb {
    private static final float ORB_IMG_SCALE;

    private static final String[] orbTextures = {
            characterPath("energyorb/layer1.png"), //When you have energy
            characterPath("energyorb/layer2.png"),
            characterPath("energyorb/layer3.png"),
            characterPath("energyorb/layer4.png"),
            characterPath("energyorb/layer5.png"),
            characterPath("energyorb/cover.png"), //"container"
            characterPath("energyorb/layer1d.png"), //When you don't have energy
            characterPath("energyorb/layer2d.png"),
            characterPath("energyorb/layer3d.png"),
            characterPath("energyorb/layer4d.png"),
            characterPath("energyorb/layer5d.png")
    };

    private static final String VFXTexture = characterPath("energyorb/vfx.png");

    //Speeds at which each layer of the energy orb texture rotates. Negative is backwards.
    private static final float[] layerSpeeds = new float[] {
            0.0F,
            0.0F,
            0.0F,
            0.0F,
            0.0F
    };

    public AugmentOrb() {
        super(orbTextures, VFXTexture, layerSpeeds);
    }

    @Override
    public void updateOrb(int energyCount) {
        for (int i = 0; i < this.angles.length; i++) {
            this.angles[i] = 0.0F;
        }


    }

    @Override
    public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
        sb.setColor(Color.WHITE);
        if (enabled) {
            sb.draw(this.energyLayers[0], current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0, 0, 0, 128, 128, false, false);
        } else {
            sb.draw(this.noEnergyLayers[0], current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0, 0, 0, 128, 128, false, false);

        }

        sb.draw(this.baseLayer, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0F, 0, 0, 128, 128, false, false);
    }

    static {
        ORB_IMG_SCALE = 1.15F * Settings.scale;
    }
}
