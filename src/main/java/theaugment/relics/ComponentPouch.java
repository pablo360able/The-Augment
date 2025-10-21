package theaugment.relics;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.character.Augment;

import static theaugment.TheAugmentMod.makeID;

public class ComponentPouch extends BaseRelic {
    private static final String NAME = ComponentPouch.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private boolean drawCardsNext = false;
    private boolean firstTurn = false;
    private static final int drawnCards = 2;

    public ComponentPouch() {
        super(ID, NAME, Augment.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + drawnCards + this.DESCRIPTIONS[1];
    }

    @Override
    public void atPreBattle() {
        this.flash();
        this.firstTurn = true;
        this.drawCardsNext = true;
        if (!this.pulse) {
            this.beginPulse();
            this.pulse = true;
        }

    }

    @Override
    public void atTurnStart() {
        this.beginPulse();
        this.pulse = true;
        if (this.drawCardsNext && !this.firstTurn) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DrawCardAction(drawnCards));
        }

        this.firstTurn = false;
        this.drawCardsNext = true;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.drawCardsNext = false;
            this.pulse = false;
        }

    }

    @Override
    public void onVictory() {
        this.pulse = false;
    }
}
