
package theaugment.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.FocusPower;

import static theaugment.TheAugmentMod.makeID;

public class ContingencyPower extends ConcentrationPower {
    public static final String POWER_ID = makeID(ContingencyPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ContingencyPower(int amount, String cardImage) {
        super(POWER_ID, TYPE, TURN_BASED, amount, cardImage);
        loadRegion("intangible");
        this.img = null;
        this.updateDescription();
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (damage >= this.owner.currentHealth) {
            return this.owner.currentHealth - 1;
        }
        return super.atDamageFinalReceive(damage, type);
    }

    @Override
    public void onRemove() {
        this.addToTop(new ApplyPowerAction(this.owner, this.owner, new FocusPower(this.owner, -this.amount)));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
