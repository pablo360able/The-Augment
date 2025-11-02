package theaugment.powers;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static theaugment.TheAugmentMod.makeID;

public class DivinePower extends BasePower implements PreDrawPower {
    public static final String POWER_ID = makeID(DivinePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public DivinePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    @Override
    public void onCardDrawPreDraw(AbstractCreature source, int amount, boolean addedToTop) {
        if (source != this.owner) {
            return;
        }

        this.addToTop(new ScryAction(this.amount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
