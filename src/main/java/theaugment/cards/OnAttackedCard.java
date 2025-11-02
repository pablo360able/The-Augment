package theaugment.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;

public interface OnAttackedCard {
    void triggerOnAttacked(DamageInfo info);
}
