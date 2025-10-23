package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.orbs.Flame;
import theaugment.util.CardStats;

public class Firewall extends BaseCard {
    public static final String ID = makeID(Firewall.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public Firewall() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasEmptyOrb()) {
            addToBot(new ChannelAction(new Flame()));
        }
        addToBot(new GainBlockAction(p, block));
        int flame = 0;
        for (AbstractOrb o : p.orbs) {
            if (o instanceof Flame) {
                flame++;
            }
        }
        addToBot(new ApplyPowerAction(p, p, new FlameBarrierPower(p, flame)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Firewall();
    }
}
