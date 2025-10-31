package theaugment.cards.powers;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.EidolonAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.EidolonFormPower;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class EidolonForm extends BaseCard {
    public static final String ID = makeID(EidolonForm.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public EidolonForm() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        AugmentPatches.AugmentCardVars.adventitious.set(this, true);
        tags.add(BaseModCardTags.FORM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EidolonFormPower(p)));
        if (upgraded) {
            addToBot(new EidolonAction(p, p.getPower(makeID(EidolonForm.class.getSimpleName())).amount));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new EidolonForm();
    }
}
