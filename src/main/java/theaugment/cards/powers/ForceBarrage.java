package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.FastDrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.cards.attacks.MagicMissile;
import theaugment.character.Augment;
import theaugment.powers.ForceBarragePower;
import theaugment.util.CardStats;

import static theaugment.util.TextureLoader.getCardTextureString;

public class ForceBarrage extends BaseCard {
    public static final String ID = makeID(ForceBarrage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 0;

    public ForceBarrage() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        setCostUpgrade(1);

        this.cardsToPreview = new MagicMissile();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ForceBarragePower(magicNumber, getCardTextureString(ID, info.cardType))));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ForceBarrage();
    }
}
