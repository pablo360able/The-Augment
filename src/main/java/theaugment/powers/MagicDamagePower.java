package theaugment.powers;

public interface MagicDamagePower {
    default float atEnchantDamage(float currentDamage) {
        return currentDamage;
    }
}
