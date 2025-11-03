package theaugment.actions;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.modifiers.MagicAttack;

import java.util.ArrayList;

import static theaugment.TheAugmentMod.modID;
import static theaugment.modifiers.AlterDescription.redescribe;

public class EnchantAction extends AbstractGameAction {
    public static final String[] TEXT;
    private final AbstractPlayer player = AbstractDungeon.player;
    private final int numberOfCards;
    private final boolean reduceCost;

    public EnchantAction(boolean reduction) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.reduceCost = reduction;
        this.numberOfCards = 1;
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

                    ArrayList<AbstractCard> cardsToMove = new ArrayList<>(attackDiscards.group);

                    for(AbstractCard c : cardsToMove) {
                        if (this.reduceCost) {
                            c.modifyCostForCombat(-1);
                        }

                        boolean alreadyMagic = false;
                        for (AbstractDamageModifier mod : DamageModifierManager.DamageModsField.damageModifiers.get(c)) {
                            if (mod instanceof MagicAttack) {
                                alreadyMagic = true;
                                break;
                            }
                        }

                        if (alreadyMagic) {
                            this.player.gainEnergy(1);
                        } else {
                            DamageModifierManager.addModifier(c, new MagicAttack());
                            redescribe(c, modID + ":Arcane.");
                        }

                        this.player.discardPile.removeCard(c);
                        this.player.hand.moveToDeck(c, false);

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
                    if (this.reduceCost) {
                        c.modifyCostForCombat(-1);
                    }

                    boolean alreadyMagic = false;
                    for (AbstractDamageModifier mod : DamageModifierManager.DamageModsField.damageModifiers.get(c)) {
                        if (mod instanceof MagicAttack) {
                            alreadyMagic = true;
                            break;
                        }
                    }

                    if (alreadyMagic) {
                        this.player.gainEnergy(1);
                    } else {
                        DamageModifierManager.addModifier(c, new MagicAttack());
                        redescribe(c, modID + ":Arcane.");
                    }

                    this.player.discardPile.removeCard(c);
                    this.player.hand.moveToDeck(c, false);

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
