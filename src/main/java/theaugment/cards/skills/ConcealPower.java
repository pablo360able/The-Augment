package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.EntropyPower;
import theaugment.util.CardStats;

public class ConcealPower extends BaseCard {
    public static final String ID = makeID(ConcealPower.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = -1;

    public ConcealPower() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            for(AbstractCard c : p.hand.group) {
                if (c.type != CardType.SKILL) {
                    canUse = false;
                    this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                    break;
                }
            }

            return canUse;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p.currentBlock));
        addToBot(new ApplyPowerAction(p, p, new EntropyPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ConcealPower();
    }
}
