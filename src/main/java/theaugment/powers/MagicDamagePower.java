package theaugment.powers;

public interface MagicDamagePower {
    public default float atEnchantDamage(float currentDamage) {
        return currentDamage;
    }
}
