package theaugment.cards.powers;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.KnowledgeIsPowerPower;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class KnowledgeIsPower extends BaseCard {
    public static final String ID = makeID(KnowledgeIsPower.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public KnowledgeIsPower() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        GraveField.grave.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new KnowledgeIsPowerPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new KnowledgeIsPower();
    }
}
