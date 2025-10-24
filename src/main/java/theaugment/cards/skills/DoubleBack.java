package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.DoubleBackAction;
import theaugment.actions.ReshuffleSpecificCardAction;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class DoubleBack extends BaseCard {
    public static final String ID = makeID(DoubleBack.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    private static final int CARDS = 1;
    private static final int UPG_CARDS = 0;

    public DoubleBack() {
        super(ID, info);

        setMagic(CARDS, UPG_CARDS);

        setExhaust(true);
        tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void upgrade() {
        super.upgrade();
        tags.remove(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoubleBackAction(magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DoubleBack();
    }
}
