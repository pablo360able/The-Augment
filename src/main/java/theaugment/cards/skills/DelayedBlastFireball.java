package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.FlameBoostAction;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.orbs.Flame;
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

        tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void upgrade() {
        super.upgrade();
        tags.remove(CustomTags.SPONTANEOUS);
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
