package theaugment.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.util.CardStats;

public class MaintainConcentration extends BaseCard {
    public static final String ID = makeID(MaintainConcentration.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            -2
    );

    public MaintainConcentration() {
        super(ID, info);
    }

    public MaintainConcentration(String cardImage) {
        super(ID, info, cardImage);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MaintainConcentration();
    }
}
