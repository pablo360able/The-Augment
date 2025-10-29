package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.LoseFocusPower;
import theaugment.powers.MagicWeaponPower;
import theaugment.util.CardStats;

public class MagicWeapon extends BaseCard {
    public static final String ID = makeID(MagicWeapon.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            0
    );
    private static final int ENCHANT = 1;
    private static final int UPG_ENCHANT = 1;
    private static final int FOCUS = 2;
    private static final int UPG_FOCUS = 2;

    public MagicWeapon() {
        super(ID, info);

        setMagic(ENCHANT, UPG_ENCHANT);
        setCustomVar("foc", FOCUS, UPG_FOCUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new FocusPower(p, 2 *magicNumber), 2 *magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new LoseFocusPower(p, 2 *magicNumber), 2 *magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new MagicWeaponPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MagicWeapon();
    }
}
