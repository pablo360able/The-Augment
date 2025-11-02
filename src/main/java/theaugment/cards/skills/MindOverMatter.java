package theaugment.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.MindOverMatterAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class MindOverMatter extends BaseCard {
    public static final String ID = makeID(MindOverMatter.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public MindOverMatter() {
        super(ID, info);

        setExhaust(true);
        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);

        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MindOverMatterAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MindOverMatter();
    }
}
