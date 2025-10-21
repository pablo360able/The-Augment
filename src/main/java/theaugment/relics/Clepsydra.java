package theaugment.relics;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theaugment.character.Augment;
import theaugment.powers.LoseFocusPower;

import static theaugment.TheAugmentMod.makeID;

public class Clepsydra extends BaseRelic {
    private static final String NAME = Clepsydra.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int NUM_TURNS = 3;
    private static final int FOCUS_AMT = 4;

    public Clepsydra() {
        super(ID, NAME, Augment.Meta.CARD_COLOR, RARITY, SOUND);
        this.counter = 0;
    }

    @Override
    public void onEquip() {
        this.counter = 0;
    }

    @Override
    public void atTurnStart() {
        if (this.counter == -1) {
            this.counter += 2;
        } else {
            ++this.counter;
        }

        if (this.counter == 3) {
            this.counter = 0;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FocusPower(AbstractDungeon.player, FOCUS_AMT)));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseFocusPower(AbstractDungeon.player, FOCUS_AMT)));
        }

    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + NUM_TURNS + this.DESCRIPTIONS[1] + FOCUS_AMT + this.DESCRIPTIONS[2] + FOCUS_AMT + this.DESCRIPTIONS[3];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Clepsydra();
    }
}
