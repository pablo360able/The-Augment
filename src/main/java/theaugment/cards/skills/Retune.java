package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.actions.RewardCardAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Retune extends BaseCard {
    public static final String ID = makeID(Retune.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Retune() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FocusPower(p, magicNumber)));
        addToBot(new RewardCardAction(CardColor.BLUE, 3, false));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Retune();
    }
}
