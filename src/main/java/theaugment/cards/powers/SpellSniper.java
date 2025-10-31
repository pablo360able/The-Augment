package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.FastDrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.SpellSniperPower;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class SpellSniper extends BaseCard {
    public static final String ID = makeID(SpellSniper.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public SpellSniper() {
        super(ID, info);

        setCostUpgrade(1);

        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SpellSniperPower()));
    }

    @Override
    public void triggerOnManualDiscard() {
        addToTop(new FastDrawCardAction(AbstractDungeon.player, 1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SpellSniper();
    }
}
