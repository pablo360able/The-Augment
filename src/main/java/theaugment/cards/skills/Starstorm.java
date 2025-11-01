package theaugment.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.cards.BaseCard;
import theaugment.cards.attacks.StarstormAttack;
import theaugment.modifiers.MagicAttack;
import theaugment.character.Augment;
import theaugment.orbs.Aether;
import theaugment.powers.StarstormPower;
import theaugment.util.CardStats;

public class Starstorm extends BaseCard {
    public static final String ID = makeID(Starstorm.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            3
    );
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 1;
    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 1;

    public Starstorm() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

        DamageModifierManager.addModifier(this, new MagicAttack());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
            addToBot(new ChannelAction(new Aether()));
        }
        StarstormAttack atk = new StarstormAttack();
        addToBot(new ApplyPowerAction(p, p, new StarstormPower(p, atk)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Starstorm();
    }
}
