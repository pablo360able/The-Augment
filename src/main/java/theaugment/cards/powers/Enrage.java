package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.FastDrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.EnragePower;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

import static theaugment.util.GeneralUtils.removePrefix;
import static theaugment.util.TextureLoader.getCardTextureString;

public class Enrage extends BaseCard {
    public static final String ID = makeID(Enrage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Enrage() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EnragePower(magicNumber, getCardTextureString(removePrefix(ID), info.cardType))));
    }

    @Override
    public void triggerOnManualDiscard() {
        addToTop(new FastDrawCardAction(AbstractDungeon.player, 1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Enrage();
    }
}
