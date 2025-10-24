package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.FatiguedPower;
import theaugment.util.CardStats;

public class BackupPlan extends BaseCard {
    public static final String ID = makeID(BackupPlan.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            3
    );

    public BackupPlan() {
        super(ID, info);

        setCostUpgrade(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SkipEnemiesTurnAction());
        addToBot(new ApplyPowerAction(p, p, new FatiguedPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BackupPlan();
    }
}
