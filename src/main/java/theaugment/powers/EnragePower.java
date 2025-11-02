package theaugment.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theaugment.TheAugmentMod.makeID;
import static theaugment.util.Helpers.IntentContains;

public class EnragePower extends ConcentrationPower {
    public static final String POWER_ID = makeID(EnragePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public EnragePower(int amount, String cardImage) {
        super(POWER_ID, TYPE, TURN_BASED, amount, cardImage);
        loadRegion("anger");
        this.img = null;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        int skilling = 0;
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped() && IntentContains(m, AbstractMonster.Intent.DEBUFF) && !IntentContains(m, AbstractMonster.Intent.ATTACK) || IntentContains(m, AbstractMonster.Intent.DEFEND)) {
                skilling++;
            }
        }
        if (skilling > 0) {
            this.flash();
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount * skilling)));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
