package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.PurgeThoughtAction;
import theaugment.actions.SuppressAction;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Suppress extends BaseCard {
    public static final String ID = makeID(Suppress.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            4
    );

    public Suppress() {
        super(ID, info);

        tags.add(CustomTags.SPONTANEOUS);
        this.exhaust = true;

        this.upgradeCost = true;
        this.costUpgrade = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SuppressAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Suppress();
    }
}
