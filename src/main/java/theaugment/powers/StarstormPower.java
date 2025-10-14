package theaugment.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.actions.UnscheduleKipAction;
import theaugment.cards.Starstorm;

import static theaugment.TheAugmentMod.makeID;

public class StarstormPower extends BasePower {
    public static final String POWER_ID = makeID(StarstormPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final Starstorm attack;

    public StarstormPower(AbstractCreature owner, Starstorm source_attack) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
        this.attack = source_attack;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        for (int i = 0; i < attack.magicNumber; i++) {
            addToBot(new AttackDamageRandomEnemyAction(attack, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.attack.damage + DESCRIPTIONS[1] + this.attack.magicNumber + DESCRIPTIONS[2];
    }
}
