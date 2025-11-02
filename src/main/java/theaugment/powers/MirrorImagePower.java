package theaugment.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

import static theaugment.TheAugmentMod.makeID;

public class MirrorImagePower extends BasePower {
    public static final String POWER_ID = makeID(MirrorImagePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MirrorImagePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != this.owner && damageAmount == 0) {
            this.addToTop(new ApplyPowerAction(this.owner, this.owner, new LoseDexterityPower(this.owner, this.amount)));
            this.addToTop(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount)));
        }
        return super.onAttacked(info, damageAmount);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
