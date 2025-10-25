package theaugment.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.FastDrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.powers.EnragePower;
import theaugment.powers.EyeOfTheStormPower;
import theaugment.util.CardStats;

import static theaugment.util.TextureLoader.getCardTextureString;

public class EyeOfTheStorm extends BaseCard {
    public static final String ID = makeID(EyeOfTheStorm.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 0;

    public EyeOfTheStorm() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        this.tags.add(CustomTags.SPONTANEOUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EyeOfTheStormPower(magicNumber, upgraded, getCardTextureString(ID, info.cardType))));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new EyeOfTheStorm();
    }
}
