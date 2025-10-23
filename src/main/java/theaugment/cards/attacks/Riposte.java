package theaugment.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.BloodAction;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.cards.OnAttackedCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Riposte extends BaseCard implements OnAttackedCard {
    public static final String ID = makeID(Riposte.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;

    public Riposte() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

        tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Riposte();
    }

    @Override
    public void triggerOnAttacked(DamageInfo info) {
        if (info.owner != AbstractDungeon.player && AbstractDungeon.player.lastDamageTaken == 0) {
            this.addToBot(new DiscardToHandAction(this));
        }
    }
}
