package theaugment.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theaugment.TheAugmentMod.makeID;

public class MagicAttack extends BasePower {
    public static final String POWER_ID = makeID(MagicAttack.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MagicAttack(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        //If NORMAL (attack) damage, modify damage by this power's amount
        return type == DamageInfo.DamageType.NORMAL ? damage - this.owner.getPower("Strength").amount + this.owner.getPower("Focus").amount : damage;
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.amount--;
            if (this.amount <= 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, makeID(MagicAttack.class.getSimpleName())));
            }
        }
    }

    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, makeID(MagicAttack.class.getSimpleName())));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (- this.owner.getPower("Strength").amount + this.owner.getPower("Focus").amount) + DESCRIPTIONS[1];
    }
}
