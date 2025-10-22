package theaugment.powers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theaugment.TheAugmentMod.makeID;

public class PotentMagicPower extends BasePower implements MagicDamagePower {
    public static final String POWER_ID = makeID(PotentMagicPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static boolean wasApplied = false;

    public PotentMagicPower(int amount) {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, amount);
        this.amount2 = amount;
        this.updateDescription();
    }

    @Override
    public float atEnchantDamage(float currentDamage) {
        if (this.amount2 > 0) {
            wasApplied = true;
            return currentDamage + (this.owner.hasPower("Focus") ? this.owner.getPower("Focus").amount : 0);
        }
        return currentDamage;
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (wasApplied) {
            this.amount2 = Math.max(this.amount2 - 1, 0);
            wasApplied = false;
        }
    }

    @Override
    public void atStartOfTurn() {
        this.amount2 = this.amount;
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}
