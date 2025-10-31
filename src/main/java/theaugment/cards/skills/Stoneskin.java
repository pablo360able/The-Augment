package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.FatiguedPower;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class Stoneskin extends BaseCard {
    public static final String ID = makeID(Stoneskin.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 16;
    private static final int UPG_BLOCK = 8;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 0;

    public Stoneskin() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);

        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new FatiguedPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Stoneskin();
    }
}
