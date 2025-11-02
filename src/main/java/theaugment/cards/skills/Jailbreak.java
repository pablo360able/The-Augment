package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.orbs.Aether;
import theaugment.powers.GainFocusPower;
import theaugment.util.CardStats;

public class Jailbreak extends BaseCard {
    public static final String ID = makeID(Jailbreak.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = -11;

    public Jailbreak() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new FocusPower(p, -magicNumber), -magicNumber));
        if (!p.hasPower("Artifact")) {
            this.addToBot(new ApplyPowerAction(p, p, new GainFocusPower(p, magicNumber), magicNumber));
        }

        int focus = p.hasPower("Focus") ? p.getPower("Focus").amount : 0;

        this.addToBot(new DrawCardAction(Math.max(focus - magicNumber, 0)));

    }

    public void applyPowers() {
        int aetherCount = 0;

        for(AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Aether) {
                ++aetherCount;
            }
        }

        if (aetherCount > 0) {
            this.baseMagicNumber = aetherCount;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Jailbreak();
    }
}
