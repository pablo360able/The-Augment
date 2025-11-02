package theaugment.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theaugment.actions.EfficiencyAction;

import static theaugment.TheAugmentMod.makeID;

public class EfficiencyPower extends BasePower {
    public static final String POWER_ID = makeID(EfficiencyPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public EfficiencyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new EfficiencyAction(this.owner, amount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
