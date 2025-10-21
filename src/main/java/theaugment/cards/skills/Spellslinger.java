package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.cards.attacks.MagicMissile;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Spellslinger extends BaseCard {
    public static final String ID = makeID(Spellslinger.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            -2
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Spellslinger() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        this.tags.add(CustomTags.SPONTANEOUS);
        this.cardsToPreview = new MagicMissile();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void triggerOnManualDiscard() {
        this.addToBot(new MakeTempCardInHandAction(new MagicMissile(), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Spellslinger();
    }
}
