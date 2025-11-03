package theaugment.modifiers;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class AlterDescription extends AbstractCardModifier {
    private final String addedText;
    private final boolean postpend;

    public AlterDescription(String addedText, boolean postpend) {
        this.addedText = addedText;
        this.postpend = postpend;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (postpend) {
            return rawDescription + " NL " + addedText;
        } else {
            return addedText + " NL " + rawDescription;
        }
    }

    public static void redescribe(AbstractCard c, String addedText, boolean postpend) {
        CardModifierManager.addModifier(c, new AlterDescription(addedText, postpend));
        c.initializeDescription();
    }

    public static void redescribe(AbstractCard c, String addedText) {
        redescribe(c, addedText, false);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AlterDescription(this.addedText, this.postpend);
    }
}
