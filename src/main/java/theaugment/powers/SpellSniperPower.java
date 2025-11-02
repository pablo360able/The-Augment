package theaugment.powers;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theaugment.TheAugmentMod.makeID;

public class SpellSniperPower extends BasePower {
    public static final String POWER_ID = makeID(SpellSniperPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public SpellSniperPower() {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, -1);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
