package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.powers.EfficiencyPower;
import theaugment.util.CardStats;

public class Efficiency extends BaseCard {
    public static final String ID = makeID(Efficiency.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 0;

    public Efficiency() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EfficiencyPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Efficiency();
    }
}
