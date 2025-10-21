package theaugment.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.RewardCardAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class FieldTest extends BaseCard {
    public static final String ID = makeID(FieldTest.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 2;

    public FieldTest() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RewardCardAction(Augment.Meta.CARD_COLOR, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FieldTest();
    }
}
