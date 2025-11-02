package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.defect.SeekAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.LoseMaxHpAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class VampiricTutor extends BaseCard {
    public static final String ID = makeID(VampiricTutor.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = -1;

    public VampiricTutor() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        setSelfRetain(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseMaxHpAction(p, magicNumber));
        addToBot(new SeekAction(1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new VampiricTutor();
    }
}
