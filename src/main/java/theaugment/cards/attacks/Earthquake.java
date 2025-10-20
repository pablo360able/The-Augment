package theaugment.cards.attacks;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.EarthquakeAction;
import theaugment.cards.BaseCard;
import theaugment.util.CardStats;

public class Earthquake extends BaseCard {
    public static final String ID = makeID(Earthquake.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            4
    );
    private static final int DAMAGE = 36;
    private static final int UPG_DAMAGE = 0;

    public Earthquake() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

        this.upgradeCost = true;
        this.costUpgrade = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // TODO: add AnimateShakeAction for monsters
        this.addToBot(new EarthquakeAction(p, this.multiDamage, this.damageTypeForTurn));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Earthquake();
    }
}
