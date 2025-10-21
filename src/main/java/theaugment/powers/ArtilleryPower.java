package theaugment.powers;


import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.cards.attacks.MagicMissile;
import theaugment.util.Helpers;

import static theaugment.TheAugmentMod.makeID;

public class ArtilleryPower extends BasePower {
    public static final String POWER_ID = makeID(ArtilleryPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final boolean upgrade;

    public ArtilleryPower(boolean upgraded) {
        super(POWER_ID + (upgraded ? "+" : ""), TYPE, TURN_BASED, AbstractDungeon.player, 1);
        this.upgrade = upgraded;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.addToTop(new LoseHPAction(this.owner, this.owner, this.amount));
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            if (this.upgrade) {
                AbstractCard s = (new MagicMissile()).makeCopy();
                s.upgrade();
                this.addToTop(new MakeTempCardInHandAction(s, this.amount));
            } else {
                this.addToTop(new MakeTempCardInHandAction(new MagicMissile(), this.amount));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
