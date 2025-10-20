package theaugment.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.MissileSprayAction;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class MissileSpray extends BaseCard {
    public static final String ID = makeID(MissileSpray.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            -1
    );
    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 1;

    public MissileSpray() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        this.cardsToPreview = new MagicMissile();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MissileSprayAction(p, this.magicNumber, this.freeToPlayOnce, this.energyOnUse));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MissileSpray();
    }
}
