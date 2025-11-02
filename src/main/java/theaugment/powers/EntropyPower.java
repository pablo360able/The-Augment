package theaugment.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static theaugment.TheAugmentMod.makeID;

public class EntropyPower extends BasePower {
    public static final String POWER_ID = makeID(EntropyPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public EntropyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + (this.amount == 1 ? DESCRIPTIONS[1] : DESCRIPTIONS[2]);
    }
}
