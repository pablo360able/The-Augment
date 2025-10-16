package theaugment.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.NewThunderStrikeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import theaugment.character.Augment;
import theaugment.orbs.Aether;
import theaugment.util.CardStats;

public class Aetherflow extends BaseCard {
    public static final String ID = makeID(Aetherflow.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 0;

    public Aetherflow() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));

        this.baseMagicNumber = 0;

        for(AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Aether) {
                ++this.baseMagicNumber;
            }
        }

        this.magicNumber = this.baseMagicNumber;

        this.addToBot(new DrawCardAction(this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Aetherflow();
    }
}
