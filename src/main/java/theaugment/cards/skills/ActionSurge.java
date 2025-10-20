package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class ActionSurge extends BaseCard {
    public static final String ID = makeID(ActionSurge.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public ActionSurge() {
        super(ID, info);

        this.exhaust = true;
        this.retain = true;
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else if (EnergyPanel.totalCount > this.costForTurn) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];

            return false;
        } else {
            return true;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ActionSurge();
    }
}
