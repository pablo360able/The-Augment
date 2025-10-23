package theaugment.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;

public interface OnAttackedCard {
    public void triggerOnAttacked(DamageInfo info);
}
