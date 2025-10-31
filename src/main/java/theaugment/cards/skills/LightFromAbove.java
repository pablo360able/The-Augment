package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.orbs.Aether;
import theaugment.util.CardStats;

public class LightFromAbove extends BaseCard {
    public static final String ID = makeID(LightFromAbove.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public LightFromAbove() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ArmamentsAction(this.upgraded));
        if (p.hasEmptyOrb()) {
            addToBot(new ChannelAction(new Aether()));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new LightFromAbove();
    }
}
