package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.RedoAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.cards.OnAttackedCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Rebuke extends BaseCard implements OnAttackedCard {
    public static final String ID = makeID(Rebuke.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    public Rebuke() {
        super(ID, info);

        tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(upgraded ? new RedoAction() : new EvokeOrbAction(1));
    }

    @Override
    public void triggerOnAttacked(DamageInfo info) {
        if (info.owner != AbstractDungeon.player && AbstractDungeon.player.lastDamageTaken > 0) {
            this.addToBot(new DiscardToHandAction(this));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Rebuke();
    }
}
