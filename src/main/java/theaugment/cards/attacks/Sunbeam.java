package theaugment.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import theaugment.cards.BaseCard;
import theaugment.modifiers.MagicAttack;
import theaugment.character.Augment;
import theaugment.orbs.Aether;
import theaugment.util.CardStats;

public class Sunbeam extends BaseCard {
    public static final String ID = makeID(Sunbeam.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Augment.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2
    );
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;

    public Sunbeam() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

        DamageModifierManager.addModifier(this, new MagicAttack());

        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChannelAction(new Aether()));
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Sunbeam();
    }
}
