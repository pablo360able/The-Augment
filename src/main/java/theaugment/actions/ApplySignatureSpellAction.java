package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theaugment.powers.SignatureSpellPower;

import java.util.ArrayList;

import static theaugment.TheAugmentMod.makeID;

public class ApplySignatureSpellAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private static final float DURATION_PER_CARD = 0.25F;
    private AbstractPlayer p;
    private ArrayList<AbstractCard> cannotSpell = new ArrayList();
    private boolean upgraded;

    public ApplySignatureSpellAction(boolean upgraded) {
        this.setValues(AbstractDungeon.player, AbstractDungeon.player);
        this.actionType = ActionType.DRAW;
        this.duration = 0.25F;
        this.p = AbstractDungeon.player;
        this.upgraded = upgraded;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for(AbstractCard c : this.p.hand.group) {
                if (!this.isSpell(c)) {
                    this.cannotSpell.add(c);
                }
            }

            if (this.cannotSpell.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.group.size() - this.cannotSpell.size() == 1) {
                for(AbstractCard c : this.p.hand.group) {
                    if (this.isSpell(c)) {
                        signSpell(c);
                        AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();

                        this.isDone = true;
                        return;
                    }
                }
            }

            this.p.hand.group.removeAll(this.cannotSpell);
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);
                this.tickDuration();
                return;
            }

            if (this.p.hand.group.size() == 1) {
                signSpell(this.p.hand.getTopCard());

                this.returnCards();
                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for(AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                signSpell(c);
            }

            this.returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        this.tickDuration();
    }

    private void returnCards() {
        for(AbstractCard c : this.cannotSpell) {
            this.p.hand.addToTop(c);
        }

        this.p.hand.refreshHandLayout();
    }

    private boolean isSpell(AbstractCard card) {
        return card.type.equals(AbstractCard.CardType.SKILL);
    }

    private void signSpell(AbstractCard card) {
        this.addToTop(new ApplyPowerAction(this.target, this.source, new SignatureSpellPower(card, this.upgraded)));
        card.rawDescription += upgraded ? "NL Put on top of your deck when played." : " NL Shuffle into your deck when played.";
        card.initializeDescription();
        this.p.hand.moveToDeck(card, !upgraded);
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID(ApplySignatureSpellAction.class.getSimpleName()));
        TEXT = uiStrings.TEXT;
    }
}