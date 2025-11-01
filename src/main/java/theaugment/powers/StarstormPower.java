package theaugment.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.cards.attacks.StarstormAttack;

import static theaugment.TheAugmentMod.makeID;

public class StarstormPower extends BasePower {
    public static final String POWER_ID = makeID(StarstormPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final StarstormAttack attack;
    private static int starstormIdOffset;

    public StarstormPower(AbstractCreature owner, StarstormAttack source_attack) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
        this.ID += starstormIdOffset++;
        this.attack = source_attack;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new NewQueueCardAction(attack, null, false, true));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        if(this.attack == null) {
            this.description = DESCRIPTIONS[0] + 5 + DESCRIPTIONS[1] + 5 + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.attack.damage + DESCRIPTIONS[1] + this.attack.magicNumber + DESCRIPTIONS[2];
        }
    }
}
