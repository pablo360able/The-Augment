package theaugment.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.character.Augment;
import theaugment.orbs.Aether;
import theaugment.util.CardStats;

public class Aetherflow extends BaseCard {
    public static final String ID = makeID(Aetherflow.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public Aetherflow() {
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
        return new Aetherflow();
    }
}
