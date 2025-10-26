package theaugment.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theaugment.TheAugmentMod.makeID;

public class SignatureSpellPower extends BasePower {
    public static final String POWER_ID = makeID(SignatureSpellPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final AbstractCard card;
    private static int signatureSpellIdOffset;
    private final boolean upgraded;

    public SignatureSpellPower(AbstractCard card, boolean upgraded) {
        super(POWER_ID, TYPE, TURN_BASED, AbstractDungeon.player, -1);
        this.ID += signatureSpellIdOffset++;
        this.card = card;
        this.upgraded = upgraded;

        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction a) {
        if (this.upgraded && c.uuid == this.card.uuid) {
            a.reboundCard = true;
        }
    }

    @Override
    public void onInitialApplication() {
        if (!this.upgraded) {
            this.card.shuffleBackIntoDrawPile = true;
        }
    }

    @Override
    public void updateDescription() {
        if(this.card == null) {
            this.description = DESCRIPTIONS[3] + (this.upgraded ? DESCRIPTIONS[1] : DESCRIPTIONS[2]);
        } else {
            this.description = DESCRIPTIONS[0] + this.card.name + (this.upgraded ? DESCRIPTIONS[1] : DESCRIPTIONS[2]);        }
    }
}
