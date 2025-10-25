
package theaugment.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;

import static theaugment.TheAugmentMod.makeID;
import static theaugment.util.Helpers.IntentContains;

public class EyeOfTheStormPower extends ConcentrationPower {
    public static final String POWER_ID = makeID(EyeOfTheStormPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final boolean upgraded;

    public EyeOfTheStormPower(int amount, boolean upgraded, String cardImage) {
        super(POWER_ID + (upgraded ? "+" : ""), TYPE, TURN_BASED, amount, cardImage);
        this.upgraded = upgraded;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        int attacking = 0;
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (IntentContains(m, AbstractMonster.Intent.ATTACK)) {
                attacking++;
            }
        }
        if (attacking > 0) {
            this.flash();
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new FocusPower(this.owner, this.amount * attacking)));
            if (!this.upgraded && !this.owner.hasPower("Artifact")) {
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new LoseFocusPower(this.owner, this.amount * attacking)));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
