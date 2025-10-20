package theaugment.util;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theaugment.orbs.Aether;

public abstract class Helpers {
    public static float EnchantDamage(float damage, DamageInfo.DamageType type) {
        AbstractPlayer p = AbstractDungeon.player;
        int strength = p.hasPower("Strength") ? p.getPower("Strength").amount : 0;
        int focus = p.hasPower("Focus") ? p.getPower("Focus").amount : 0;
        return type == DamageInfo.DamageType.NORMAL ? damage + focus - strength : damage;
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
