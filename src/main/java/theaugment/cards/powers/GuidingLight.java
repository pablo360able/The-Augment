package theaugment.cards.powers;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.EidolonAction;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.powers.EidolonFormPower;
import theaugment.powers.GuidingLightPower;
import theaugment.util.CardStats;

public class GuidingLight extends BaseCard {
    public static final String ID = makeID(GuidingLight.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public GuidingLight() {
        super(ID, info);

        this.upgradeCost = true;
        this.costUpgrade = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GuidingLightPower()));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new GuidingLight();
    }
}
