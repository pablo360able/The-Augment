package theaugment.ui;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import theaugment.ui.CampfireTrainEffect;

import static theaugment.TheAugmentMod.makeID;

public class TrainOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public TrainOption(boolean active) {
        this.label = TEXT[0];
        this.usable = active;
        this.description = active ? TEXT[1] : TEXT[2];
        this.img = ImageMaster.CAMPFIRE_TRAIN_BUTTON;
    }

    @Override
    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new CampfireTrainEffect());
        }

    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID(TrainOption.class.getSimpleName()));
        TEXT = uiStrings.TEXT;
    }
}
