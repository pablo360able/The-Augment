package theaugment.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.DoubleBackAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.AugmentPatches;
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

    public DoubleBack() {
        super(ID, info);

        setExhaust(true);
        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);
    }

    @Override
    public void upgrade() {
        super.upgrade();
        AugmentPatches.AugmentCardVars.spontaneous.set(this, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoubleBackAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DoubleBack();
    }
}
