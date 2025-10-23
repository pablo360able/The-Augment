package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.FastDrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Surprise extends BaseCard {
    public static final String ID = makeID(Surprise.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;

    public Surprise() {
        super(ID, info);

        setMagic(DRAW, UPG_DRAW);

        setInnate(true);
        setExhaust(true);
        tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        baseMagicNumber = magicNumber;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.getIntentBaseDmg() < 0) {
                baseMagicNumber++;
            }
        }
        addToBot(new DrawCardAction(p, baseMagicNumber));
    }

    @Override
    public void triggerOnManualDiscard() {
        addToTop(new FastDrawCardAction(AbstractDungeon.player, 1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Surprise();
    }
}
