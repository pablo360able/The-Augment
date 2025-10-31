package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class FastCast extends BaseCard {
    public static final String ID = makeID(FastCast.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int DRAW = 1;
    private static final int UPG_DRAW = 1;

    public FastCast() {
        super(ID, info);

        setMagic(DRAW, UPG_DRAW);

        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EvokeOrbAction(1));
        addToBot(new DrawCardAction(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FastCast();
    }
}
