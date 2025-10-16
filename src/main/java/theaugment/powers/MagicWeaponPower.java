package theaugment.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theaugment.cards.CustomTags;
import theaugment.util.Helpers;

import static theaugment.TheAugmentMod.makeID;

public class MagicWeaponPower extends BasePower {
    public static final String POWER_ID = makeID(MagicWeaponPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MagicWeaponPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        //If NORMAL (attack) damage, modify damage by this power's amount
        return !card.hasTag(CustomTags.MAGIC) ? Helpers.EnchantDamage(damage, type) : damage;
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.amount--;
            if (this.amount <= 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            }
        }
    }

    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
