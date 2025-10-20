package theaugment.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import theaugment.cards.BaseCard;
import theaugment.modifiers.MagicAttack;
import theaugment.actions.BloodAction;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class StunningStrike extends BaseCard {
    public static final String ID = makeID(StunningStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 1;
    private static final int DEXTERITY = 2;
    private static final int UPG_DEXTERITY = 1;

    public StunningStrike() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DEXTERITY, UPG_DEXTERITY);

        tags.add(CardTags.STRIKE);
        DamageModifierManager.addModifier(this, new MagicAttack());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 2; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            AbstractGameAction[] tempDex = {
                    new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber), magicNumber),
                    new ApplyPowerAction(p, p, new LoseDexterityPower(p, magicNumber), magicNumber)
            };
            this.addToBot(new BloodAction(m, p, tempDex));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new StunningStrike();
    }
}
