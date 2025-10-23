package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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

public class Shield extends BaseCard implements OnAttackedCard {
    public static final String ID = makeID(Shield.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 3;

    public Shield() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);

        tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    @Override
    public void triggerOnAttacked(DamageInfo info) {
        if (info.owner != AbstractDungeon.player && AbstractDungeon.player.lastDamageTaken == 0) {
            this.addToBot(new DiscardToHandAction(this));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Shield();
    }
}
