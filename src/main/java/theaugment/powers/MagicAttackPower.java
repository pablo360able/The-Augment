package theaugment.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theaugment.cards.CustomTags;

import static theaugment.TheAugmentMod.makeID;

public class MagicAttackPower extends BasePower {
    public static final String POWER_ID = makeID(MagicAttackPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MagicAttackPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        //If NORMAL (attack) damage, modify damage by this power's amount
        int strength = this.owner.hasPower("Strength") ? this.owner.getPower("Strength").amount : 0;
        int focus = this.owner.hasPower("Focus") ? this.owner.getPower("Focus").amount : 0;
        return type == DamageInfo.DamageType.NORMAL && !card.hasTag(CustomTags.MAGIC) ? damage + focus - strength : damage;
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
