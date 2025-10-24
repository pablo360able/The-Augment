package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.EntropyPower;
import theaugment.powers.LoseArtifactPower;
import theaugment.util.CardStats;

public class Protection extends BaseCard {
    public static final String ID = makeID(Protection.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 0;

    public Protection() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded && !p.hasPower(EntropyPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new LoseArtifactPower(p, magicNumber)));
        }
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Protection();
    }
}
