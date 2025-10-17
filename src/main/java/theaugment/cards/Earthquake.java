package theaugment.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateShakeAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import modifiers.MagicAttack;
import theaugment.actions.BloodAction;
import theaugment.actions.EarthquakeAction;
import theaugment.util.CardStats;

public class Earthquake extends BaseCard {
    public static final String ID = makeID(Earthquake.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            4
    );
    private static final int DAMAGE = 36;
    private static final int UPG_DAMAGE = 0;

    public Earthquake() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void upgrade() {
        this.baseCost = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // TODO: add AnimateShakeAction for monsters
        this.addToBot(new EarthquakeAction(p, this.multiDamage, this.damageTypeForTurn));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Earthquake();
    }
}
