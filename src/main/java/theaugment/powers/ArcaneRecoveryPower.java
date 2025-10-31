package theaugment.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.util.AugmentPatches;

import static theaugment.TheAugmentMod.makeID;

public class ArcaneRecoveryPower extends BasePower {
    public static final String POWER_ID = makeID(ArcaneRecoveryPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ArcaneRecoveryPower() {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, -1);
        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction act) {
        if (act.exhaustCard && !AugmentPatches.AugmentCardVars.spontaneous.get(card)) {
            AugmentPatches.AugmentCardVars.spontaneous.set(card, true);
            card.rawDescription = "Spontaneous. NL " + card.rawDescription;
            card.initializeDescription();
            act.exhaustCard = false;
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
