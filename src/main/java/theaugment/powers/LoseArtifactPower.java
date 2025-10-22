package theaugment.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theaugment.util.GeneralUtils;

import static theaugment.TheAugmentMod.makeID;

public class LoseArtifactPower extends BasePower {
    public static final String POWER_ID = makeID(LoseArtifactPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public LoseArtifactPower(AbstractCreature owner, int newAmount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, newAmount);
        String unPrefixed = GeneralUtils.removePrefix(POWER_ID);
        this.updateDescription();
        this.loadRegion("flex");
        this.img = null;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        if (this.owner.hasPower("Artifact")) {
            AbstractPower artifactPower = this.owner.getPower("Artifact");
            if (artifactPower.amount <= this.amount) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, artifactPower));
            } else {
                artifactPower.amount -= this.amount;
                this.owner.updatePowers();
            }
        }
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
}
