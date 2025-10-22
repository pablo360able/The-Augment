package theaugment.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theaugment.actions.BloodAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.modifiers.MagicAttack;
import theaugment.util.CardStats;

public class FaerieFire extends BaseCard {
    public static final String ID = makeID(FaerieFire.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            1
    );
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public FaerieFire() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

        setExhaust(true);
        DamageModifierManager.addModifier(this, new MagicAttack());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new BloodAction(mo, p, new ApplyPowerAction(mo, p, new VulnerablePower(mo, magicNumber, false))));
            addToBot(new BloodAction(mo, p, new ApplyPowerAction(mo, p, new FrailPower(mo, magicNumber, false))));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FaerieFire();
    }
}
