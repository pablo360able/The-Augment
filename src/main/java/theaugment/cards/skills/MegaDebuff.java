package theaugment.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class MegaDebuff extends BaseCard {
    public static final String ID = makeID(MegaDebuff.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public MegaDebuff() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        GraveField.grave.set(this, true);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AllEnemyApplyPowerAction(p, magicNumber, (mo) -> new WeakPower(mo, magicNumber, false)));
        addToBot(new AllEnemyApplyPowerAction(p, magicNumber, (mo) -> new VulnerablePower(mo, magicNumber, false)));
        addToBot(new AllEnemyApplyPowerAction(p, magicNumber, (mo) -> new FrailPower(mo, magicNumber, false)));

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MegaDebuff();
    }
}
