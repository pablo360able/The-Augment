package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.FastDrawCardAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.actions.LoseMaxHpAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.ContingencyPower;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

import static theaugment.util.GeneralUtils.removePrefix;
import static theaugment.util.TextureLoader.getCardTextureString;

public class Contingency extends BaseCard {
    public static final String ID = makeID(Contingency.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0
    );

    public Contingency() {
        super(ID, info);

        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = p.maxHealth / 2;
        addToBot(new RemoveDebuffsAction(p));
        addToBot(new LoseMaxHpAction(p, amount));
        addToBot(new ApplyPowerAction(p, p, new FocusPower(p, amount)));
        addToBot(new ApplyPowerAction(p, p, new ContingencyPower(amount, getCardTextureString(removePrefix(ID), info.cardType))));
        for (AbstractCard c : p.masterDeck.group) {
            if (c.uuid == uuid) {
                p.masterDeck.removeCard(c);
            }
        }
    }

    @Override
    public void triggerOnManualDiscard() {
        if (upgraded) {
            addToTop(new FastDrawCardAction(AbstractDungeon.player, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Contingency();
    }
}
