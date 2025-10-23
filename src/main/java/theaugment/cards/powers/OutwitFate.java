package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.OutwitFatePower;
import theaugment.util.CardStats;

public class OutwitFate extends BaseCard {
    public static final String ID = makeID(OutwitFate.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public OutwitFate() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new OutwitFatePower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new OutwitFate();
    }
}
