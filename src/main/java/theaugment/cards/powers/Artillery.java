package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.cards.attacks.MagicMissile;
import theaugment.character.Augment;
import theaugment.powers.ArtilleryPower;
import theaugment.util.CardStats;

public class Artillery extends BaseCard {
    public static final String ID = makeID(Artillery.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public Artillery() {
        super(ID, info);

        this.cardsToPreview = new MagicMissile();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ArtilleryPower(upgraded)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Artillery();
    }
}
