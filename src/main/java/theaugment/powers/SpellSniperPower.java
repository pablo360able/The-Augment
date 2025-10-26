package theaugment.powers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.modifiers.MagicAttack;

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
