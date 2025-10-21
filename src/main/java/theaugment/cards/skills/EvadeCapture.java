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

public class EvadeCapture extends BaseCard {
    public static final String ID = makeID(EvadeCapture.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            2
    );
    private static final int BLOCK = 12;
    private static final int UPG_BLOCK = 3;

    public EvadeCapture() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);

        setExhaust(true);
        this.tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    @Override
    public void triggerOnManualDiscard() {
        this.baseBlock *= 2;
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new EvadeCapture();
    }
}
