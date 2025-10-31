package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.FlameBoostAction;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.orbs.Flame;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class DelayedBlastFireball extends BaseCard {
    public static final String ID = makeID(DelayedBlastFireball.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public DelayedBlastFireball() {
        super(ID, info);

        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);
    }

    @Override
    public void upgrade() {
        super.upgrade();
        AugmentPatches.AugmentCardVars.spontaneous.set(this, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasEmptyOrb()) {
            addToBot(new ChannelAction(new Flame()));
        }
        addToBot(new FlameBoostAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DelayedBlastFireball();
    }
}
