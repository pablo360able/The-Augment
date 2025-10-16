package theaugment.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.StunningStrikeAction;
import theaugment.character.Augment;
import theaugment.powers.MagicAttackPower;
import theaugment.util.CardStats;

public class StunningStrike extends BaseCard {
    public static final String ID = makeID(StunningStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 1;
    private static final int DEXTERITY = 2;
    private static final int UPG_DEXTERITY = 1;

    public StunningStrike() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DEXTERITY, UPG_DEXTERITY);

        tags.add(CardTags.STRIKE);
        tags.add(CustomTags.MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 2; i++) {
            addToBot(new StunningStrikeAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new StunningStrike();
    }
}
