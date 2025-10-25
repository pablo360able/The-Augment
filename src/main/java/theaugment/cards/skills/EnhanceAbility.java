package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.cards.powers.DemonsMight;
import theaugment.cards.powers.EchosGrace;
import theaugment.cards.powers.WraithsCunning;
import theaugment.character.Augment;
import theaugment.util.CardStats;

import java.util.ArrayList;

public class EnhanceAbility extends BaseCard {
    public static final String ID = makeID(EnhanceAbility.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public EnhanceAbility() {
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
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new DemonsMight());
        stanceChoices.add(new WraithsCunning());
        stanceChoices.add(new EchosGrace());

        this.addToBot(new ChooseOneAction(stanceChoices));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new EnhanceAbility();
    }
}
