package theaugment.util;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theaugment.orbs.Aether;
import theaugment.powers.MagicDamagePower;

public abstract class Helpers {
    public static float EnchantDamage(float damage, DamageInfo.DamageType type) {
        if (type != DamageInfo.DamageType.NORMAL) {
            return damage;
        }
        AbstractPlayer p = AbstractDungeon.player;
        int strength = p.hasPower("Strength") ? p.getPower("Strength").amount : 0;
        int focus = p.hasPower("Focus") ? p.getPower("Focus").amount : 0;
        float magicDamage = damage + focus - strength;
        for (AbstractPower power : p.powers) {
            if (power instanceof MagicDamagePower) {
                magicDamage = ((MagicDamagePower)power).atEnchantDamage(magicDamage);
            }
        }
        return magicDamage;
    }

    public static int AetherChanneled() {
        int num = 0;

        for(AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Aether) {
                num++;
            }
        }

        return num;
    }
}
