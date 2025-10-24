package theaugment.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.FastDrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import theaugment.cards.BaseCard;
import theaugment.cards.CustomTags;
import theaugment.character.Augment;
import theaugment.orbs.Aether;
import theaugment.powers.GainFocusPower;
import theaugment.util.CardStats;

public class PowerWordKill extends BaseCard {
    public static final String ID = makeID(PowerWordKill.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            4
    );
    private static final int MAGIC = 100;
    private static final int UPG_MAGIC = 0;

    public PowerWordKill() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        tags.add(CustomTags.SPONTANEOUS);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new JudgementAction(m, magicNumber));
    }

    @Override
    public void triggerOnManualDiscard() {
        if (upgraded) {
            addToTop(new FastDrawCardAction(AbstractDungeon.player, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PowerWordKill();
    }
}
