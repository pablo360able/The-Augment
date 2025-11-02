package theaugment.cards.attacks;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.GreatCleaveAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class GreatCleave extends BaseCard {
    public static final String ID = makeID(GreatCleave.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            3
    );
    private static final int DAMAGE = 21;
    private static final int UPG_DAMAGE = 4;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public GreatCleave() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GreatCleaveAction(multiDamage, damageTypeForTurn, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new GreatCleave();
    }
}
