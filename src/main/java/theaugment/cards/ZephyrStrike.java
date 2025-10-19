package theaugment.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theaugment.modifiers.MagicAttack;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class ZephyrStrike extends BaseCard {
    public static final String ID = makeID(ZephyrStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2
    );
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 0;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public ZephyrStrike() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

        DamageModifierManager.addModifier(this, new MagicAttack());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, i % 2 == 0 ? AbstractGameAction.AttackEffect.SLASH_HORIZONTAL : AbstractGameAction.AttackEffect.SLASH_DIAGONAL, true));
        }
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ZephyrStrike();
    }
}
