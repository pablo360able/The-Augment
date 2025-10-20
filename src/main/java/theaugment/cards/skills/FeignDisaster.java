package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class FeignDisaster extends BaseCard {
    public static final String ID = makeID(FeignDisaster.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int BLOCK = 18;
    private static final int UPG_BLOCK = 6;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public FeignDisaster() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);

        tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new BlurPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new EntanglePower(p)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FeignDisaster();
    }
}
