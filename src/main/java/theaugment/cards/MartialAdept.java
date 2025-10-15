package theaugment.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import theaugment.character.Augment;
import theaugment.powers.MartialAdeptPower;
import theaugment.powers.PowerUpPower;
import theaugment.util.CardStats;

public class MartialAdept extends BaseCard {
    public static final String ID = makeID(MartialAdept.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    private static final int DRAW = 1;
    private static final int UPG_DRAW = 1;

    public MartialAdept() {
        super(ID, info);

        setMagic(DRAW, UPG_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!p.hasPower(ID)) {
            addToBot(new ApplyPowerAction(p, p, new MartialAdeptPower(p, magicNumber)));
        }
        else {
            p.getPower(ID).amount += magicNumber;
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MartialAdept();
    }
}
