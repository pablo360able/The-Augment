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
import theaugment.powers.WardingPower;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class Warding extends BaseCard {
    public static final String ID = makeID(Warding.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 0;

    public Warding() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new WardingPower(p, magicNumber)));
        if (upgraded) {
            int artifactDown = magicNumber;
            if (p.hasPower(EntropyPower.POWER_ID)) {
                artifactDown -= p.getPower(EntropyPower.POWER_ID).amount;
            }
            if (artifactDown > 0) {
                addToBot(new ApplyPowerAction(p, p, new LoseArtifactPower(p, artifactDown)));
            }
            addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, magicNumber)));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Warding();
    }
}
