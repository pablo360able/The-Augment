package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theaugment.cards.BaseCard;
import theaugment.character.Augment;
import theaugment.powers.EntropyPower;
import theaugment.util.CardStats;

public class Mitigation extends BaseCard {
    public static final String ID = makeID(Mitigation.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            1
    );
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Mitigation() {
        super(ID, info);

        setBlock(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.getIntentBaseDmg() >= 0) {
                addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, magicNumber * 2, false)));
            }
            if (mo.intent == AbstractMonster.Intent.DEBUFF || mo.intent == AbstractMonster.Intent.ATTACK_DEBUFF || mo.intent == AbstractMonster.Intent.DEFEND_DEBUFF) {
                addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, magicNumber * 2, false)));
            }
            if (mo.intent == AbstractMonster.Intent.ATTACK_DEFEND || mo.intent == AbstractMonster.Intent.DEFEND || mo.intent == AbstractMonster.Intent.DEFEND_BUFF || mo.intent == AbstractMonster.Intent.DEFEND_DEBUFF) {
                addToBot(new ApplyPowerAction(mo, p, new FrailPower(mo, magicNumber * 2, false)));
            }
            if (mo.intent == AbstractMonster.Intent.BUFF || mo.intent == AbstractMonster.Intent.ATTACK_BUFF || mo.intent == AbstractMonster.Intent.DEFEND_BUFF) {
                addToBot(new ApplyPowerAction(mo, p, new EntropyPower(mo, magicNumber)));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Mitigation();
    }
}
