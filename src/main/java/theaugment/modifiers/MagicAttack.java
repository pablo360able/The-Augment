package theaugment.modifiers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.powers.SpellSniperPower;
import theaugment.util.Helpers;

import static theaugment.TheAugmentMod.makeID;

public class MagicAttack extends AbstractDamageModifier {
    public boolean smart;

    public static final String ID = makeID(MagicAttack.class.getSimpleName());

    public MagicAttack() {
        this(true);
    }

    public MagicAttack(boolean smart) {
        this.smart = smart;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        return Helpers.EnchantDamage(damage, type, smart);
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        if (AbstractDungeon.player.hasPower(SpellSniperPower.POWER_ID)) {
            damage += Math.min(damage, target.currentBlock);
        }
        return damage;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new MagicAttack(this.smart);
    }

    @Override
    public boolean isInherent() {
        return true;
    }
}
