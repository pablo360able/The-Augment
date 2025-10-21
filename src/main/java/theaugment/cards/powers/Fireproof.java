package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.powers.FireproofPower;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Fireproof extends BaseCard {
    public static final String ID = makeID(Fireproof.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Fireproof() {
        super(ID, info);

        this.upgradeCost = true;
        this.costUpgrade = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FireproofPower(p)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Fireproof();
    }
}
