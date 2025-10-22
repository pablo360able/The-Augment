package theaugment.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.defect.DecreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.ArcaneSmiteAction;
import theaugment.actions.QuadratureAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.modifiers.MagicAttack;
import theaugment.util.CardStats;

public class ArcaneSmite extends BaseCard {
    public static final String ID = makeID(ArcaneSmite.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            -1
    );
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 5;

    public ArcaneSmite() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

        DamageModifierManager.addModifier(this, new MagicAttack());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ArcaneSmiteAction(p, m, this.damage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
        if (p.maxOrbs > 0) {
            this.addToBot(new DecreaseMaxOrbAction(1));
        } else {
            this.exhaustOnUseOnce = true;
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ArcaneSmite();
    }
}
