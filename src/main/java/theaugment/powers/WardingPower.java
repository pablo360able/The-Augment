package theaugment.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static theaugment.TheAugmentMod.makeID;

public class WardingPower extends BasePower {
    public static final String POWER_ID = makeID(WardingPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public WardingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (!this.owner.hasPower(EntropyPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new LoseArtifactPower(this.owner, this.amount)));
        }
        addToBot(new ApplyPowerAction(this.owner, this.owner, new ArtifactPower(this.owner, this.amount)));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
