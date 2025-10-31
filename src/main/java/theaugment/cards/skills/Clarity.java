package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.actions.ClarityAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class Clarity extends BaseCard {
    public static final String ID = makeID(Clarity.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );

    public Clarity() {
        super(ID, info);

        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);
        setExhaust(true);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FocusPower(p, -1)));
        addToBot(new ClarityAction());
        if (!p.hasPower("Focus") || p.getPower("Focus").amount <= 1) {
            addToBot(new PressEndTurnButtonAction());
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Clarity();
    }
}
