package theaugment.util;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theaugment.orbs.Aether;
import theaugment.powers.MagicDamagePower;

public abstract class Helpers {
    public static int SCRIED_AWAY_THIS_TURN = 0;

    public static float EnchantDamage(float damage, DamageInfo.DamageType type) {
        return EnchantDamage(damage, type, false);
    }

    public static float EnchantDamage(float damage, DamageInfo.DamageType type, boolean smart) {
        if (type != DamageInfo.DamageType.NORMAL) {
            return damage;
        }
        AbstractPlayer p = AbstractDungeon.player;
        int strength = p.hasPower("Strength") ? p.getPower("Strength").amount : 0;
        int focus = p.hasPower("Focus") ? p.getPower("Focus").amount : 0;
        if (smart && strength > focus) {
            return damage;
        }
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

    public static boolean IntentContains(AbstractMonster m, AbstractMonster.Intent intent) {
        if (intent == AbstractMonster.Intent.ATTACK) {
            return m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND;
        }
        else if (intent == AbstractMonster.Intent.DEFEND) {
            return m.intent == AbstractMonster.Intent.DEFEND || m.intent == AbstractMonster.Intent.DEFEND_BUFF || m.intent == AbstractMonster.Intent.DEFEND_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEFEND;
        } else if (intent == AbstractMonster.Intent.BUFF) {
            return m.intent == AbstractMonster.Intent.BUFF || m.intent == AbstractMonster.Intent.ATTACK_BUFF || m.intent == AbstractMonster.Intent.DEFEND_BUFF;
        } else if (intent == AbstractMonster.Intent.DEBUFF) {
            return m.intent == AbstractMonster.Intent.DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.DEFEND_DEBUFF || m.intent == AbstractMonster.Intent.STRONG_DEBUFF;
        } else {
            return m.intent == intent;
        }
    }
}
