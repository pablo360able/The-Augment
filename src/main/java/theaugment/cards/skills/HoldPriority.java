package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.HoldingPower;
import theaugment.util.CardStats;

public class HoldPriority extends BaseCard {
    public static final String ID = makeID(HoldPriority.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int BLOCK = 21;
    private static final int UPG_BLOCK = 9;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 0;

    public HoldPriority() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new HoldingPower(p, magicNumber)));
        addToBot(new PressEndTurnButtonAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new HoldPriority();
    }
}
