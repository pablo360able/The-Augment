package theaugment.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.BonusActionAction;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class BonusAction extends BaseCard {
    public static final String ID = makeID(BonusAction.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public BonusAction() {
        super(ID, info);

        this.exhaust = true;
        tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BonusActionAction(p, upgraded));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BonusAction();
    }
}
