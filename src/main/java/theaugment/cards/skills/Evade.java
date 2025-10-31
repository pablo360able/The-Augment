package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class Evade extends BaseCard {
    public static final String ID = makeID(Evade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 2;
    private static final int SCRY = 1;
    private static final int UPG_SCRY = 1;

    public Evade() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(SCRY, UPG_SCRY);

        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ScryAction(magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Evade();
    }
}
