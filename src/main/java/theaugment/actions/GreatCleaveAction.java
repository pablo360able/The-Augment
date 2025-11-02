package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GreatCleaveAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final int[] multiDamage;
    private final DamageInfo.DamageType type;
    private final int bonusDamage;
    private final AttackEffect effect;

    public GreatCleaveAction(int[] multiDamage, DamageInfo.DamageType type, int bonusDamage) {
        this.p = AbstractDungeon.player;
        this.multiDamage = multiDamage;
        this.type = type;
        this.bonusDamage = bonusDamage;
        this.effect = AttackEffect.SLASH_HORIZONTAL;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.EXHAUST;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            CardGroup toRemove = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            for (AbstractCard c : this.p.hand.group) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    toRemove.addToTop(c);
                    for (int i = 0; i < this.multiDamage.length; i++) {
                        multiDamage[i] += this.bonusDamage;
                    }
                }
            }
            for (AbstractCard c : toRemove.group) {
                this.p.hand.moveToDiscardPile(c);
            }

            this.addToTop(new DamageAllEnemiesAction(this.p, this.multiDamage, this.type, this.effect));
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }


        this.tickDuration();
    }
}
