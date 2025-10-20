package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import theaugment.actions.MultiattackAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.PowerUpPower;
import theaugment.util.CardStats;

public class Multiattack extends BaseCard {
    public static final String ID = makeID(Multiattack.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            3
    );

    public Multiattack() {
        super(ID, info);
    }

    @Override
    public void upgrade() {
        this.baseCost = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MultiattackAction());
        for (AbstractCard c : p.hand.group) {
            if (c.type == CardType.ATTACK) {
                c.costForTurn = 0;
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Multiattack();
    }
}
