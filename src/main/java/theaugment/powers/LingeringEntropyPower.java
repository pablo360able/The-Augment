package theaugment.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static theaugment.TheAugmentMod.makeID;

public class LingeringEntropyPower extends BasePower {
    public static final String POWER_ID = makeID(LingeringEntropyPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public LingeringEntropyPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
        this.isPostActionPower = true;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + (this.amount == 1 ? DESCRIPTIONS[1] : DESCRIPTIONS[2]);
    }
}
