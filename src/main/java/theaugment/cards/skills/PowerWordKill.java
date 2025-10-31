package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.FastDrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.AugmentPatches;
import theaugment.util.CardStats;

public class PowerWordKill extends BaseCard {
    public static final String ID = makeID(PowerWordKill.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            4
    );
    private static final int MAGIC = 100;
    private static final int UPG_MAGIC = 0;

    public PowerWordKill() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        AugmentPatches.AugmentCardVars.spontaneous.set(this, true);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new JudgementAction(m, magicNumber));
    }

    @Override
    public void triggerOnManualDiscard() {
        if (upgraded) {
            addToTop(new FastDrawCardAction(AbstractDungeon.player, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PowerWordKill();
    }
}
