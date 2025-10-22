package theaugment.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FocusPower;

import static theaugment.TheAugmentMod.makeID;

public class KnowledgeIsPowerPower extends BasePower {
    public static final String POWER_ID = makeID(KnowledgeIsPowerPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public KnowledgeIsPowerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (DrawCardAction.drawnCards.size() != 1 || AbstractDungeon.actionManager.actions.get(0) instanceof DrawCardAction) {
            return;
        }

        addToBot(new ApplyPowerAction(owner, owner, new FocusPower(owner, amount)));
        addToBot(new ApplyPowerAction(owner, owner, new LoseFocusPower(owner, amount)));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
