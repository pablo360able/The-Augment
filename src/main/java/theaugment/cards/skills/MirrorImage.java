package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.MirrorImagePower;
import theaugment.util.CardStats;

public class MirrorImage extends BaseCard {
    public static final String ID = makeID(MirrorImage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 0;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public MirrorImage() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);

        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new GainBlockAction(p, block));
        }
        addToBot(new ApplyPowerAction(p, p, new MirrorImagePower(p, 2)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MirrorImage();
    }
}
