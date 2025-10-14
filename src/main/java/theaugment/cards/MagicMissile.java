package theaugment.cards;

import com.megacrit.cardcrawl.actions.common.MagicMissileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.StunningStrikeAction;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class MagicMissile extends MagicAttack {
    public static final String ID = makeID(MagicMissile.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 8;

    public MagicMissile() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

        this.exhaust = true;
        tags.add(CustomTags.MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        new MagicMissileAction(m, new DamageInfo(m, damage, DamageInfo.DamageType.NORMAL));

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MagicMissile();
    }
}
