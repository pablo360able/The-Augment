package theaugment.actions;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
import java.util.Map;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class RewardCardAction extends AbstractGameAction {
    public final AbstractCard.CardColor color;
    public final boolean toAdd;
    public final int count;
    private boolean retrieveCard = false;

    public RewardCardAction(AbstractCard.CardColor color, int num, boolean addToHand) {
        this.amount = 1;
        this.count = num;
        this.color = color;
        this.toAdd = addToHand;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = num == 1 ? Settings.ACTION_DUR_XFAST : Settings.ACTION_DUR_FAST;
    }

    public RewardCardAction(AbstractCard.CardColor color, int num) {this(color, num, true);}

    @Override
    public void update() {
        ArrayList<AbstractCard> generatedCards = new ArrayList<>();

        while(generatedCards.size() != this.count) {
            boolean dupe = false;
            AbstractCard tmp = null;
            tmp = returnTrulyRandomCardRewardByColor(this.color);

            for(AbstractCard c : generatedCards) {
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe) {
                generatedCards.add(tmp.makeCopy());
            }
        }

        if (this.toAdd) {

            if (this.count == 1) {
                AbstractCard chosen = generatedCards.get(0);

                if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
                    chosen.upgrade();
                }

                addToBot(new MakeTempCardInHandAction(chosen));

                ArrayList<AbstractCard> card = new ArrayList<>();
                card.add(chosen);
                AbstractDungeon.getCurrRoom().rewards.add(StSLib.generateCardReward(card, false));

                this.isDone = true;
            } else {
                if (this.duration == Settings.ACTION_DUR_FAST) {
                    AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], false);
                    this.tickDuration();
                } else {
                    if (!this.retrieveCard) {
                        if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                            AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                            AbstractCard disCard2 = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                            if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
                                disCard.upgrade();
                            }

                            disCard.setCostForTurn(0);
                            disCard2.setCostForTurn(0);
                            disCard.current_x = -1000.0F * Settings.xScale;
                            disCard2.current_x = -1000.0F * Settings.xScale + AbstractCard.IMG_HEIGHT_S;
                            if (this.amount == 1) {
                                if (AbstractDungeon.player.hand.size() < 10) {
                                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                                } else {
                                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                                }

                                AbstractCard var4 = null;
                            } else if (AbstractDungeon.player.hand.size() + this.amount <= 10) {
                                AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float)Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                                AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard2, (float)Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                            } else if (AbstractDungeon.player.hand.size() == 9) {
                                AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float)Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                                AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard2, (float)Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                            } else {
                                AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, (float)Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                                AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard2, (float)Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                            }

                            ArrayList<AbstractCard> card = new ArrayList<>();
                            card.add(disCard);
                            AbstractDungeon.getCurrRoom().rewards.add(StSLib.generateCardReward(card, false));

                            AbstractDungeon.cardRewardScreen.discoveryCard = null;
                        }

                        this.retrieveCard = true;
                    }

                    this.tickDuration();
                }

            }
        } else {
            AbstractDungeon.getCurrRoom().rewards.add(StSLib.generateCardReward(generatedCards, false));
            this.isDone = true;
        }
    }

    public static AbstractCard returnTrulyRandomCardRewardByColor(AbstractCard.CardColor color) {
        ArrayList<AbstractCard> tmpPool = new ArrayList<>();

        for(Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
            AbstractCard card = c.getValue();
            if (card.color == color && card.rarity != AbstractCard.CardRarity.BASIC && (!UnlockTracker.isCardLocked(c.getKey()) || Settings.treatEverythingAsUnlocked())) {
                tmpPool.add(card);
            }
        }

        return tmpPool.get(cardRandomRng.random(tmpPool.size() - 1));
    }
}
