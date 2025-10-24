package theaugment.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.RepressAction;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Repress extends BaseCard {
    public static final String ID = makeID(Repress.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            4
    );

    public Repress() {
        super(ID, info);

        tags.add(CustomTags.SPONTANEOUS);
        this.exhaust = true;

        setCostUpgrade(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RepressAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Repress();
    }
}
