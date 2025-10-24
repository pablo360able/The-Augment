package theaugment.powers;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theaugment.actions.BloodAction;
import theaugment.actions.ConcentrationAction;

import static theaugment.TheAugmentMod.makeID;

public abstract class ConcentrationPower extends BasePower {
    public ConcentrationPower(String POWER_ID, PowerType TYPE, boolean TURN_BASED, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, amount);
    }

    @Override
    public void onInitialApplication() {
        for (AbstractPower p : this.owner.powers) {
            if (p instanceof ConcentrationPower && !p.ID.equals(this.ID)) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, p));
            }
        }
    }

    @Override
    public void atStartOfTurn() {
        this.addToTop(new ConcentrationAction(this));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
