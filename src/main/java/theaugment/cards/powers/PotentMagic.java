package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.PotentMagicPower;
import theaugment.util.CardStats;

public class PotentMagic extends BaseCard {
    public static final String ID = makeID(PotentMagic.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public PotentMagic() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PotentMagicPower(magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PotentMagic();
    }
}
