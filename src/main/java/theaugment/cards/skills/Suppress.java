package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.PurgeThoughtAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Suppress extends BaseCard {
    public static final String ID = makeID(Suppress.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;

    public Suppress() {
        super(ID, info);

        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p, p, 1));
        addToBot(new PurgeThoughtAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Suppress();
    }
}
