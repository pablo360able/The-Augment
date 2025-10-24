package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class BlankSlate extends BaseCard {
    public static final String ID = makeID(BlankSlate.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ALL,
            2
    );

    public BlankSlate() {
        super(ID, info);

        setExhaust(true);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RemoveAllPowersAction(p, false));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new RemoveAllPowersAction(mo, false));
        }
        addToBot(new PressEndTurnButtonAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BlankSlate();
    }
}
