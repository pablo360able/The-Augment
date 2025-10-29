package theaugment.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theaugment.cards.BaseCard;
import theaugment.modifiers.MagicAttack;
import theaugment.actions.BloodAction;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class ShockingGrasp extends BaseCard {
    public static final String ID = makeID(ShockingGrasp.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public ShockingGrasp() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

        DamageModifierManager.addModifier(this, new MagicAttack());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.LIGHTNING));
        addToBot(new BloodAction(m, p, new ApplyPowerAction(m, p, new StrengthPower(m, -magicNumber))));
        if (!m.hasPower("Artifact")) {
            addToBot(new BloodAction(m, p, new ApplyPowerAction(m, p, new GainStrengthPower(m, magicNumber))));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ShockingGrasp();
    }
}
