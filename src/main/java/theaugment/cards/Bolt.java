package theaugment.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Bolt extends BaseCard {
    public static final String ID = makeID(Bolt.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            2
    );
    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 12;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 2;

    public Bolt() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setBlock(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new MakeTempCardInHandAction(new MagicMissile(), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Bolt();
    }
}
