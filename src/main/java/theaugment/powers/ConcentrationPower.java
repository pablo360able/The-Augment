package theaugment.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.actions.ConcentrationAction;

public abstract class ConcentrationPower extends BasePower {
    public final String cardImage;

    public ConcentrationPower(String POWER_ID, PowerType TYPE, boolean TURN_BASED, int amount, String cardImage) {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, amount);
        this.cardImage = cardImage;
    }

    @Override
    public void onInitialApplication() {
        for (AbstractPower p : this.owner.powers) {
            if (p instanceof ConcentrationPower && !p.ID.equals(this.ID)) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, p));
            }
        }
    }

    @Override
    public void atStartOfTurn() {
        this.addToTop(new ConcentrationAction(this));
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.owner != this.owner && damageAmount > 0) {
            if (this.owner.hasPower("Focus")) {
                this.addToTop(new ApplyPowerAction(this.owner, this.owner, new FocusPower(this.owner, -1)));
            } else {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
        }
        return super.onAttacked(info, damageAmount);
    }
}
