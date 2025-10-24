package theaugment.powers;


import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.cards.attacks.MagicMissile;

import static theaugment.TheAugmentMod.makeID;

public class FatiguedPower extends BasePower {
    public static final String POWER_ID = makeID(FatiguedPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public FatiguedPower(AbstractCreature owner, int turns) {
        super(POWER_ID, TYPE, TURN_BASED, owner, turns);
        if (this.amount >= 999) {
            this.amount = 999;
        }
        loadRegion("fasting");
        this.img = null;
        this.updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 999) {
            this.amount = 999;
        }
    }

    @Override
    public void onEnergyRecharge() {
        this.flash();
        AbstractDungeon.player.loseEnergy(1);
        if (--this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            this.updateDescription();
        }
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
}
