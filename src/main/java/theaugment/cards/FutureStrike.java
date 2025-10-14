package theaugment.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.actions.BloodAction;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class FutureStrike extends BaseCard {
    public static final String ID = makeID(FutureStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;

    public FutureStrike() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new BloodAction(m, p, new ScryAction(0), true));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FutureStrike();
    }
}
