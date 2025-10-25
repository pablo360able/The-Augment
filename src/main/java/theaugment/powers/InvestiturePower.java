
package theaugment.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;

import static theaugment.TheAugmentMod.makeID;

public class InvestiturePower extends ConcentrationPower {
    public static final String POWER_ID = makeID(InvestiturePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public InvestiturePower(int amount, String cardImage) {
        super(POWER_ID, TYPE, TURN_BASED, amount, cardImage);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.player.orbs.isEmpty()) {
            this.flash();

            for(AbstractOrb o : AbstractDungeon.player.orbs) {
                o.onStartOfTurn();
                o.onEndOfTurn();
            }
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
