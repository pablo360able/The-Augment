package theaugment.relics;


import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.character.Augment;
import theaugment.modifiers.MagicAttack;
import theaugment.powers.MagicWeaponPower;
import theaugment.util.Helpers;

import static theaugment.TheAugmentMod.makeID;

public class Arthame extends BaseRelic {
    private static final String NAME = Arthame.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public Arthame() {
        super(ID, NAME, Augment.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public float atDamageModify(float damage, AbstractCard card) {
        //If NORMAL (attack) damage that isn't already magic, apply magic damage
        for (AbstractDamageModifier mod : DamageModifierManager.modifiers(card)) {
            if (mod instanceof MagicAttack) {
                return damage;
            }
        }
        if (AbstractDungeon.player.getPower(MagicWeaponPower.POWER_ID) != null) {
            return damage;
        }
        if (card.freeToPlay() || card.costForTurn == 0) {
            return Helpers.EnchantDamage(damage, card.damageTypeForTurn);
        }
        return damage;
    }
}
