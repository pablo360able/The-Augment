package theaugment.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.actions.UnscheduleKipAction;

import static theaugment.TheAugmentMod.makeID;

public class KnowledgeIsPowerPower extends BasePower {
    public static final String POWER_ID = makeID(KnowledgeIsPowerPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static boolean SCHEDULED = false;

    public KnowledgeIsPowerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (!SCHEDULED) {
            SCHEDULED = true;
            addToBot(new ApplyPowerAction(owner, owner, new FocusPower(owner, amount)));
            addToBot(new UnscheduleKipAction(this));
        }
    }

    public void unschedule() {
        SCHEDULED = false;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
