package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theaugment.cards.BaseCard;
import theaugment.powers.ConcentrationPower;
import theaugment.util.CardStats;

public class DropConcentration extends BaseCard {
    public static final String ID = makeID(DropConcentration.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            -2
    );

    public DropConcentration() {
        super(ID, info);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        for (AbstractPower pow : AbstractDungeon.player.powers) {
            if (pow instanceof ConcentrationPower) {
                addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, pow));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DropConcentration();
    }
}
