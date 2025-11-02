package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.util.AugmentPatches;

import java.util.ArrayList;

public class BonusActionAction extends AbstractGameAction {
    public static final String[] TEXT;
    private final AbstractPlayer player;
    private final int numberOfCards = 1;
    private final boolean forCombat;

    public BonusActionAction(AbstractPlayer p, boolean permanentReduction) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = p;
        this.forCombat = permanentReduction;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            CardGroup spontaneousDiscards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.player.discardPile.group) {
                if (AugmentPatches.AugmentCardVars.spontaneous.get(c)) {
                    spontaneousDiscards.addToBottom(c);
                }
            }
            if (!spontaneousDiscards.isEmpty()) {
                if (spontaneousDiscards.size() <= this.numberOfCards) {

                    ArrayList<AbstractCard> cardsToMove = new ArrayList<>(spontaneousDiscards.group);

                    for(AbstractCard c : cardsToMove) {
                        if (this.player.hand.size() < 10) {
                            this.player.hand.addToHand(c);
                            if (this.forCombat) {
                                this.addToBot(new ReduceCostAction(c.uuid, 1));
                            } else {
                                this.addToBot(new ReduceCostForTurnAction(c, 1));
                            }

                            this.player.discardPile.removeCard(c);
                        }

                        c.lighten(false);
                        c.applyPowers();
                    }

                    this.isDone = true;
                } else {
                    AbstractDungeon.gridSelectScreen.open(spontaneousDiscards, this.numberOfCards, TEXT[0], false);

                    this.tickDuration();
                }
            } else {
                this.isDone = true;
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for(AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    if (this.player.hand.size() < 10) {
                        this.player.hand.addToHand(c);
                        if (this.forCombat) {
                            this.addToBot(new ReduceCostAction(c));
                        } else {
                            this.addToBot(new ReduceCostForTurnAction(c, 1));
                        }

                        this.player.discardPile.removeCard(c);
                    }

                    c.lighten(false);
                    c.unhover();
                    c.applyPowers();
                }

                for(AbstractCard c : this.player.discardPile.group) {
                    c.unhover();
                    c.target_x = (float) CardGroup.DISCARD_PILE_X;
                    c.target_y = 0.0F;
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
            if (this.isDone) {
                for(AbstractCard c : this.player.hand.group) {
                    c.applyPowers();
                }
            }

        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("BetterToHandAction").TEXT;
    }
}
