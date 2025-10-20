package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.orbs.Aether;
import theaugment.util.CardStats;

public class Illuminate extends BaseCard {
    public static final String ID = makeID(Illuminate.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public Illuminate() {
        super(ID, info);

        tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChannelAction(new Aether()));
    }

    @Override
    public void upgrade() {
        tags.remove(CustomTags.SPONTANEOUS);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Illuminate();
    }
}
