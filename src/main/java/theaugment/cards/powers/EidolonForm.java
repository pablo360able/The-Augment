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

        tags.add(CustomTags.ADVENTITIOUS);
        tags.add(BaseModCardTags.FORM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        EidolonFormPower eidol = p.hasPower(makeID("EidolonFormPower")) ? (EidolonFormPower)p.getPower(makeID("EidolonFormPower")) : new EidolonFormPower(p);
        addToBot(new ApplyPowerAction(p, p, eidol));
        if (upgraded) {
            addToBot(new EidolonAction(p, eidol.amount));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new EidolonForm();
    }
}
