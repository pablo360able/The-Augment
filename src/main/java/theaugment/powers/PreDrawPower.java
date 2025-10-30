package theaugment.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

public interface PreDrawPower {
    public void onCardDrawPreDraw(AbstractCreature source, int amount, boolean addedToTop);
}
