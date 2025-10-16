package theaugment.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MagicMissileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import modifiers.MagicAttack;
import theaugment.orbs.Aether;
import theaugment.util.CardStats;

public class Spellstrike extends BaseCard {
    public static final String ID = makeID(Spellstrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 2;

    public Spellstrike() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

        tags.add(CardTags.STRIKE);
        DamageModifierManager.addModifier(this, new MagicAttack());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseMagicNumber = 0;

        for(AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Aether) {
                ++this.baseMagicNumber;
            }
        }

        this.magicNumber = this.baseMagicNumber;

        for (int i = 0; i < this.magicNumber; i++) {
            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Spellstrike();
    }
}
