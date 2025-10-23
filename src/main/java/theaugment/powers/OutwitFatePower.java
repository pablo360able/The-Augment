package theaugment.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.List;

import static theaugment.TheAugmentMod.makeID;

public class OutwitFatePower extends BasePower {
    public static final String POWER_ID = makeID(OutwitFatePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public OutwitFatePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    @Override
    public void onDrawOrDiscard() {
        if (AbstractDungeon.actionManager.currentAction instanceof ScryAction) {
            this.addToTop(new GainBlockAction(this.owner, this.owner, this.amount));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
