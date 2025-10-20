package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RagePower;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.util.CardStats;

public class StepOfTheWind extends BaseCard {
    public static final String ID = makeID(StepOfTheWind.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;

    public StepOfTheWind() {
        super(ID, info);

        setMagic(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_WHIRLWIND"));
        addToBot(new VFXAction(new WhirlwindEffect(), 0.0F));
        addToBot(new ApplyPowerAction(p, p, new RagePower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new StepOfTheWind();
    }
}
