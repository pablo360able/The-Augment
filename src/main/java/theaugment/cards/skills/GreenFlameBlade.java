package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.orbs.Flame;
import theaugment.powers.GreenFlamePower;
import theaugment.util.CardStats;

public class GreenFlameBlade extends BaseCard {
    public static final String ID = makeID(GreenFlameBlade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 3;

    public GreenFlameBlade() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasEmptyOrb()) {
            addToBot(new ChannelAction(new Flame()));
        }
        addToBot(new ApplyPowerAction(p, p, new GreenFlamePower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new GreenFlameBlade();
    }
}
