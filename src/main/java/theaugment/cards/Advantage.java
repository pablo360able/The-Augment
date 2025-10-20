package theaugment.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Advantage extends BaseCard {
    public static final String ID = makeID(Advantage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;

    public Advantage() {
        super(ID, info);

        setMagic(DRAW, UPG_DRAW);

        tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        this.addToBot(new DiscardAction(p, p, 1, false));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Advantage();
    }
}
