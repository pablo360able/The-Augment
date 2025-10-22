package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class RayOfEnfeeblement extends BaseCard {
    public static final String ID = makeID(RayOfEnfeeblement.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public RayOfEnfeeblement() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
        addToBot(new ApplyPowerAction(m, p, new FrailPower(m, magicNumber, false)));
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new RayOfEnfeeblement();
    }
}
