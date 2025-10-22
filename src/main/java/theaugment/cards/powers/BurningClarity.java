package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.BurningClarityPower;
import theaugment.util.CardStats;

public class BurningClarity extends BaseCard {
    public static final String ID = makeID(BurningClarity.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public BurningClarity() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BurningClarityPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BurningClarity();
    }
}
