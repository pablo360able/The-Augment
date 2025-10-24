package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.ReadyAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class ReadiedAction extends BaseCard {
    public static final String ID = makeID(ReadiedAction.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int CARDS = 1;
    private static final int UPG_CARDS = 1;

    public ReadiedAction() {
        super(ID, info);

        setMagic(CARDS, UPG_CARDS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PressEndTurnButtonAction());
        addToBot(new ReadyAction(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ReadiedAction();
    }
}
