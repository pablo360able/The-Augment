package theaugment.modifiers;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class SignatureSpellModifier extends AbstractCardModifier {
    private final boolean shuffle;
    private final boolean initiallyShuffled;

    public SignatureSpellModifier(boolean upgraded, boolean initiallyShuffled) {
        this.shuffle = !upgraded;
        this.initiallyShuffled = initiallyShuffled;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (shuffle) {
            card.shuffleBackIntoDrawPile = true;
        }
    }

    @Override
    public void onRemove(AbstractCard card) {
        if (shuffle) {
            card.shuffleBackIntoDrawPile = initiallyShuffled;
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (!initiallyShuffled) {
            return rawDescription + " NL " + (shuffle ? "Shuffle into " : "Put on top of ") + " your deck when played.";
        } else if (!shuffle) {
            return rawDescription.replace("Shuffle into", "Put on top of");
        } else {
            return rawDescription;
        }
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        if (!shuffle)  {
            action.reboundCard = true;
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new SignatureSpellModifier(!shuffle, initiallyShuffled);
    }

    public static void apply(AbstractCard card, boolean upgraded) {
        CardModifierManager.addModifier(card, new SignatureSpellModifier(upgraded, card.shuffleBackIntoDrawPile));
    }
}
