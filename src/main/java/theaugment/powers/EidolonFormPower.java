package theaugment.powers;


import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.actions.EidolonAction;

import java.util.ArrayList;
import java.util.List;

import static theaugment.TheAugmentMod.makeID;

public class EidolonFormPower extends BasePower {
    public static final String POWER_ID = makeID(EidolonFormPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static final List<AbstractCard> affected = new ArrayList<AbstractCard>();

    public EidolonFormPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 1);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new EidolonAction(this, amount));
    }

    public void markCard(AbstractCard c) {
        affected.add(c);
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        for (AbstractCard ac : affected) {
            if (AbstractDungeon.player.hand.contains(ac)) {
                addToTop(new DiscardSpecificCardAction(ac));
            }
        }
    }

    @Override
    public void onRemove() {
        affected.clear();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
