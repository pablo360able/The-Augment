
package theaugment.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.cards.attacks.MagicMissile;

import static theaugment.TheAugmentMod.makeID;

public class ForceBarragePower extends ConcentrationPower {
    public static final String POWER_ID = makeID(ForceBarragePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ForceBarragePower(int amount, String cardImage) {
        super(POWER_ID, TYPE, TURN_BASED, amount, cardImage);
        this.updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof MagicMissile) {
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new FocusPower(this.owner, this.amount)));
            if (!this.owner.hasPower("Artifact")) {
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new LoseFocusPower(this.owner, this.amount)));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
