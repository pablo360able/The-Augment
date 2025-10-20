package theaugment.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.character.Augment;
import theaugment.powers.MagicWeaponPower;
import theaugment.util.CardStats;

public class GuardianBlade extends BaseCard {
    public static final String ID = makeID(GuardianBlade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3;

    public GuardianBlade() {
        super(ID, info);

        this.retain = true;
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new MagicWeaponPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new GuardianBlade();
    }
}
