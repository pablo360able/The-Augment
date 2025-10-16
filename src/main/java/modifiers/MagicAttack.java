package modifiers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.util.Helpers;

import static theaugment.TheAugmentMod.makeID;

public class MagicAttack extends AbstractDamageModifier {
    public static final String ID = makeID(MagicAttack.class.getSimpleName());

    public MagicAttack() {}

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        return Helpers.EnchantDamage(damage, type);
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new MagicAttack();
    }
}
