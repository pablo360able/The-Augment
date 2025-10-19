package theaugment.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.modifiers.MagicAttack;
import theaugment.actions.BloodAction;
import theaugment.util.CardStats;

public class Soulrend extends BaseCard {
    public static final String ID = makeID(Soulrend.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 17;
    private static final int UPG_DAMAGE = 4;

    public Soulrend() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

        this.exhaust = true;
        DamageModifierManager.addModifier(this, new MagicAttack());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        addToBot(new BloodAction(m, p, new IncreaseMaxOrbAction(1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Soulrend();
    }
}
