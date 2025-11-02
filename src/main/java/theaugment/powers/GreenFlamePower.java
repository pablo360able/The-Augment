package theaugment.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theaugment.actions.BloodAction;

import static theaugment.TheAugmentMod.makeID;

public class GreenFlamePower extends BasePower {
    public static final String POWER_ID = makeID(GreenFlamePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public GreenFlamePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type != DamageInfo.DamageType.NORMAL || target == this.owner) {
            return;
        }
        this.addToTop(new BloodAction(target, this.owner, new DamageAllEnemiesAction((AbstractPlayer)this.owner, this.amount, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE)));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
