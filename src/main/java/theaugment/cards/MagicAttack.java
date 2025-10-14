package theaugment.cards;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theaugment.util.CardStats;

public abstract class MagicAttack extends BaseCard {
    public MagicAttack(String ID, CardStats info) {
        super(ID, info);
        tags.add(CustomTags.MAGIC);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        AbstractPower focus = AbstractDungeon.player.getPower("Focus");

        if (strength != null) {
            int current_strength = strength.amount;
            strength.amount = (focus != null) ? focus.amount : 0;
            super.calculateCardDamage(mo);
            strength.amount = current_strength;
        } else {
            AbstractDungeon.player.addPower(new StrengthPower(AbstractDungeon.player, (focus != null) ? focus.amount : 0));
            super.calculateCardDamage(mo);
            AbstractDungeon.player.getPower("Strength").amount = 0;
        }
    }
}

