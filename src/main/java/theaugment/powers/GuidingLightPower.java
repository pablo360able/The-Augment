package theaugment.powers;

import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.util.Helpers;

import static theaugment.TheAugmentMod.makeID;

public class GuidingLightPower extends BasePower {
    public static final String POWER_ID = makeID(GuidingLightPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public GuidingLightPower() {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, 1);
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer && !AbstractDungeon.player.hand.isEmpty() && !AbstractDungeon.player.hasRelic("Runic Pyramid") && !AbstractDungeon.player.hasPower("Equilibrium")) {
            this.addToBot(new RetainCardsAction(this.owner, this.amount * Helpers.AetherChanneled()));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount * Helpers.AetherChanneled() + DESCRIPTIONS[1];
    }
}
