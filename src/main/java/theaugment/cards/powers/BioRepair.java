package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.MaxHpIfRegenPower;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class BioRepair extends BaseCard {
    public static final String ID = makeID(BioRepair.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public BioRepair() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new RegenPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new MaxHpIfRegenPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BioRepair();
    }
}
