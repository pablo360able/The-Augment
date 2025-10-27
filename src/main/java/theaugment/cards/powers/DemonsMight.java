package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theaugment.cards.BaseCard;
import theaugment.powers.DemonsMightPower;
import theaugment.powers.EntropyPower;
import theaugment.util.CardStats;

import static theaugment.util.GeneralUtils.removePrefix;
import static theaugment.util.TextureLoader.getCardTextureString;

public class DemonsMight extends BaseCard {
    public static final String ID = makeID(DemonsMight.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            -2
    );

    public DemonsMight() {
        super(ID, info);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower("Focus") && p.getPower("Focus").amount > 0) {
            int focus = p.getPower("Focus").amount;
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, focus)));
            if (!p.hasPower("Artifact") && !p.hasPower(EntropyPower.POWER_ID)) {
                addToBot(new ApplyPowerAction(p, p, new DemonsMightPower(focus, getCardTextureString(removePrefix(ID), info.cardType))));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DemonsMight();
    }
}
