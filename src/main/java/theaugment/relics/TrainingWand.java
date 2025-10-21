package theaugment.relics;


import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Girya;
import com.megacrit.cardcrawl.relics.PeacePipe;
import com.megacrit.cardcrawl.relics.Shovel;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.LiftOption;
import theaugment.character.Augment;
import theaugment.modifiers.MagicAttack;
import theaugment.ui.TrainOption;
import theaugment.util.Helpers;

import java.util.ArrayList;

import static theaugment.TheAugmentMod.makeID;

public class TrainingWand extends BaseRelic {
    private static final String NAME = TrainingWand.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;
    public static final int FOC_LIMIT = 3;

    public TrainingWand() {
        super(ID, NAME, Augment.Meta.CARD_COLOR, RARITY, SOUND);
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        if (this.counter != 0) {
            this.flash();
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FocusPower(AbstractDungeon.player, this.counter), this.counter));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }

    }

    @Override
    public boolean canSpawn() {
        if (AbstractDungeon.floorNum >= 48 && !Settings.isEndless) {
            return false;
        } else {
            int campfireRelicCount = 0;

            for(AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof PeacePipe || r instanceof Shovel || r instanceof Girya) {
                    ++campfireRelicCount;
                }
            }

            return campfireRelicCount < 2;
        }
    }

    @Override
    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
        options.add(new TrainOption(this.counter < FOC_LIMIT));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TrainingWand();
    }
}
