package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.PowerUpPower;
import theaugment.util.CardStats;

public class PowerUp extends BaseCard {
    public static final String ID = makeID(PowerUp.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public PowerUp() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new NoBlockPower(p, 2, false)));
        if (!p.hasPower(makeID("PowerUpPower"))) {
            addToBot(new ApplyPowerAction(p, p, new PowerUpPower(p)));
        }
    }

    @Override
    public void upgrade() {
        this.selfRetain = true;
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PowerUp();
    }
}
