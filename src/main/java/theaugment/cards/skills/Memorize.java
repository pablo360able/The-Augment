package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.MemorizeAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Memorize extends BaseCard {
    public static final String ID = makeID(Memorize.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Memorize() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setInnate(true);
        setCustomVar("scry", 1, 0);

        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MemorizeAction(this.uuid, 1));
        addToBot(new ScryAction(customVar("scry")));
        addToBot(new DrawCardAction(p, magicNumber));
    }

    public void learn(int scryIncrease) {
        setCustomVar("scry", customVar("scry") + scryIncrease, 0);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Memorize();
    }
}
