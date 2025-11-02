package theaugment.cards.attacks;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;
import theaugment.util.Helpers;

public class FatedBlow extends BaseCard {
    public static final String ID = makeID(FatedBlow.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            3
    );
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 0;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public FatedBlow() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void didDiscard() {
        if (AbstractDungeon.actionManager.currentAction instanceof ScryAction) {
            this.setCostForTurn(this.costForTurn - 1);
        }
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        this.setCostForTurn(this.cost - Helpers.SCRIED_AWAY_THIS_TURN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FatedBlow();
    }
}
