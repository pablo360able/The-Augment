package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.cards.attacks.MagicMissile;
import theaugment.character.Augment;
import theaugment.powers.ArtilleryPower;
import theaugment.util.CardStats;

public class DefensiveStance extends BaseCard {
    public static final String ID = makeID(DefensiveStance.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    private static int MAGIC = 2;
    private static int UPG_MAGIC = 1;

    public DefensiveStance() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        this.tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber - 4)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DefensiveStance();
    }
}
