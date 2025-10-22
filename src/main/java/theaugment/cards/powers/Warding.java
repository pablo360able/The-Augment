package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.powers.LoseArtifactPower;
import theaugment.powers.MaxHpIfRegenPower;
import theaugment.powers.WardingPower;
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

        this.tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new WardingPower(p, magicNumber)));
        if (upgraded) {
            addToBot(new ApplyPowerAction(p, p, new LoseArtifactPower(p, magicNumber)));
            addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, magicNumber)));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Warding();
    }
}
