package theaugment.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.actions.MissileSprayAction;
import theaugment.actions.RecenterAction;
import theaugment.cards.BaseCard;
import theaugment.cards.attacks.MagicMissile;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class Recenter extends BaseCard {
    public static final String ID = makeID(Recenter.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            -1
    );
    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 1;

    public Recenter() {
        super(ID, info);

        this.exhaust = true;
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RecenterAction(p, this.magicNumber, this.freeToPlayOnce, this.energyOnUse));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Recenter();
    }
}
