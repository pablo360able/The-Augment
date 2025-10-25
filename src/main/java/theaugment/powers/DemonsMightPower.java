
package theaugment.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theaugment.TheAugmentMod.makeID;

public class DemonsMightPower extends ConcentrationPower {
    public static final String POWER_ID = makeID(DemonsMightPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public DemonsMightPower(int amount, String cardImage) {
        super(POWER_ID, TYPE, TURN_BASED, amount, cardImage);
        loadRegion("demonForm");
        this.img = null;
        this.updateDescription();
    }

    @Override
    public void onRemove() {
        this.addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -this.amount)));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
