package theaugment.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.ImprovisedAssaultAction;
import theaugment.cards.BaseCard;
import theaugment.cards.attacks.MagicMissile;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class ImprovisedAssault extends BaseCard {
    public static final String ID = makeID(ImprovisedAssault.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public ImprovisedAssault() {
        super(ID, info);

        setExhaust(true);

        cardsToPreview = new MagicMissile();
    }

    @Override
    public void upgrade() {
        super.upgrade();
        cardsToPreview.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ImprovisedAssaultAction(upgraded));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ImprovisedAssault();
    }
}
