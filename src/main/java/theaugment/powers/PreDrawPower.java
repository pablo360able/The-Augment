package theaugment.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

public interface PreDrawPower {
    void onCardDrawPreDraw(AbstractCreature source, int amount, boolean addedToTop);
}
