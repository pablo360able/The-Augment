package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.ReshuffleSpecificCardAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class CircleBack extends BaseCard {
    public static final String ID = makeID(CircleBack.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public CircleBack() {
        super(ID, info);

        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int powers = 0;
        for (AbstractCard c : p.discardPile.group) {
            if (c.type == CardType.POWER) {
                powers++;
                addToBot(new ReshuffleSpecificCardAction(c));
            }
        }
        if (powers > 0) {
            addToBot(new ScryAction(powers));
        }
        addToBot(upgraded ? new ExpertiseAction(p, 10) : new DrawCardAction(3));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CircleBack();
    }
}
