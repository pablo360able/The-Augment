package theaugment.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import theaugment.actions.BloodAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class RegenerativeBreaking extends BaseCard {
    public static final String ID = makeID(RegenerativeBreaking.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;

    public RegenerativeBreaking() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i <= 2; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
            this.addToBot(new BloodAction(m, p, new GainEnergyAction(1)));
        }
        addToBot(new ApplyPowerAction(p, p, new EntanglePower(p)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new RegenerativeBreaking();
    }
}
