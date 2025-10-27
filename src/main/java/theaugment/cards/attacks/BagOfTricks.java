package theaugment.cards.attacks;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.cards.skills.Badger;
import theaugment.cards.skills.Dove;
import theaugment.character.Augment;
import theaugment.util.CardStats;

import java.util.ArrayList;

public class BagOfTricks extends BaseCard {
    public static final String ID = makeID(BagOfTricks.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 0;

    public BagOfTricks() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> bagChoices = new ArrayList<>();
        bagChoices.add(new Rat());
        bagChoices.add(new Badger());
        bagChoices.add(new Dove());
        if (this.upgraded) {
            for(AbstractCard c : bagChoices) {
                c.upgrade();
            }
        }

        this.addToBot(new ChooseOneAction(bagChoices));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BagOfTricks();
    }
}
