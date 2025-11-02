package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.DivinePower;
import theaugment.util.CardStats;

public class Divine extends BaseCard {
    public static final String ID = makeID(Divine.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 2;

    public Divine() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DivinePower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Divine();
    }
}
