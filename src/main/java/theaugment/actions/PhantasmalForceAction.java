package theaugment.actions;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.modifiers.MagicAttack;

import java.util.ArrayList;

public class PhantasmalForceAction extends AbstractGameAction {
    public static final String[] TEXT;
    public final int numberOfCards = 1;
    private final AbstractPlayer player = AbstractDungeon.player;

    public PhantasmalForceAction(AbstractMonster m, int amount) {
        this.target = m;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            CardGroup attackDiscards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.player.discardPile.group) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    attackDiscards.addToBottom(c);
                }
            }
            if (!attackDiscards.isEmpty()) {
                if (attackDiscards.size() <= this.numberOfCards) {

                    for(AbstractCard c : attackDiscards.group) {

                        this.player.discardPile.removeCard(c);
                        for (int i = 0; i < this.amount; i++) {
                            AbstractCard tmp = c.makeSameInstanceOf();
                            AbstractDungeon.player.limbo.addToBottom(tmp);
                            tmp.current_x = c.current_x;
                            tmp.current_y = c.current_y;
                            tmp.target_x = (float)Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                            tmp.target_y = (float)Settings.HEIGHT / 2.0F;
                            if (this.target != null) {
                                tmp.calculateCardDamage((AbstractMonster)this.target);
                            }

                            tmp.purgeOnUse = true;
                            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, (AbstractMonster)this.target, c.energyOnUse, true, true), true);

                        }
                        this.player.exhaustPile.addToTop(c);

                        c.lighten(false);
                        c.applyPowers();
                    }

                    this.isDone = true;
                } else {
                    AbstractDungeon.gridSelectScreen.open(attackDiscards, this.numberOfCards, TEXT[0], false);

                    this.tickDuration();
                }
            } else {
                this.isDone = true;
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for(AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {

                    this.player.discardPile.removeCard(c);
                    for (int i = 0; i < this.amount; i++) {
                        AbstractCard tmp = c.makeSameInstanceOf();
                        AbstractDungeon.player.limbo.addToBottom(tmp);
                        tmp.current_x = c.current_x;
                        tmp.current_y = c.current_y;
                        tmp.target_x = (float)Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                        tmp.target_y = (float)Settings.HEIGHT / 2.0F;
                        if (this.target != null) {
                            tmp.calculateCardDamage((AbstractMonster)this.target);
                        }

                        tmp.purgeOnUse = true;
                        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, (AbstractMonster)this.target, c.energyOnUse, true, true), true);

                    }
                    this.player.exhaustPile.addToTop(c);

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
        TEXT = CardCrawlGame.languagePack.getUIString("DiscardPileToTopOfDeckAction").TEXT;
    }
}
