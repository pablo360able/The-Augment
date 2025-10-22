package theaugment.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.BloodAction;
import theaugment.actions.PhantasmalForceAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.modifiers.MagicAttack;
import theaugment.util.CardStats;

public class PhantasmalForce extends BaseCard {
    public static final String ID = makeID(PhantasmalForce.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            3
    );
    private static final int DAMAGE = 13;
    private static final int UPG_DAMAGE = 0;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public PhantasmalForce() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

        DamageModifierManager.addModifier(this, new MagicAttack());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.LIGHTNING));
        addToBot(new BloodAction(m, p, new PhantasmalForceAction(m, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PhantasmalForce();
    }
}
