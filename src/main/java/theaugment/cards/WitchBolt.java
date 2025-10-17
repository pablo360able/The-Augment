package theaugment.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.StrengthPower;
import modifiers.MagicAttack;
import theaugment.actions.BloodAction;
import theaugment.orbs.Aether;
import theaugment.util.CardStats;

public class WitchBolt extends BaseCard {
    public static final String ID = makeID(WitchBolt.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 3;

    public WitchBolt() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

        DamageModifierManager.addModifier(this, new MagicAttack());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p, p, 1));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.LIGHTNING));
        addToBot(new BloodAction(m, p, new MakeTempCardInDrawPileAction(this.makeStatEquivalentCopy(), 1, false, true)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new WitchBolt();
    }
}
