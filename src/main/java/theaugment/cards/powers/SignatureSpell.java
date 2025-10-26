package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.ApplySignatureSpellAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.DivinePower;
import theaugment.util.CardStats;

public class SignatureSpell extends BaseCard {
    public static final String ID = makeID(SignatureSpell.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public SignatureSpell() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplySignatureSpellAction(upgraded));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SignatureSpell();
    }
}
