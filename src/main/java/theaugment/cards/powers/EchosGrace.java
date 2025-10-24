package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.powers.EchosGracePower;
import theaugment.powers.EntropyPower;
import theaugment.powers.LoseArtifactPower;
import theaugment.powers.WardingPower;
import theaugment.util.CardStats;

public class EchosGrace extends BaseCard {
    public static final String ID = makeID(EchosGrace.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            -2
    );

    public EchosGrace() {
        super(ID, info);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower("Focus") && p.getPower("Focus").amount > 0) {
            int focus = p.getPower("Focus").amount;
            addToBot(new ApplyPowerAction(p, p, new FocusPower(p, focus)));
            if (!p.hasPower("Artifact") && !p.hasPower(EntropyPower.POWER_ID)) {
                addToBot(new ApplyPowerAction(p, p, new EchosGracePower(focus)));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new EchosGrace();
    }
}
