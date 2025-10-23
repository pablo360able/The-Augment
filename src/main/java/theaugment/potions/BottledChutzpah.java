package theaugment.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theaugment.powers.LoseFocusPower;
import theaugment.util.AugmentEnum;

import static theaugment.TheAugmentMod.makeID;

public class BottledChutzpah extends BasePotion {
    public static final String ID = makeID(BottledChutzpah.class.getSimpleName());

    private static final Color LIQUID_COLOR = Color.BROWN.cpy();
    private static final Color HYBRID_COLOR = null;
    private static final Color SPOTS_COLOR = Color.TAN.cpy();

    public BottledChutzpah() {
        super(ID, 1, PotionRarity.COMMON, PotionSize.BOTTLE, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        playerClass = AugmentEnum.AUGMENT;
    }

    @Override
    public String getDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        AbstractCreature var2 = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            int strength = AbstractDungeon.player.hasPower("Strength") ? AbstractDungeon.player.getPower("Strength").amount : 0;
            int dexterity = AbstractDungeon.player.hasPower("Dexterity") ? AbstractDungeon.player.getPower("Dexterity").amount : 0;
            int focus = AbstractDungeon.player.hasPower("Focus") ? AbstractDungeon.player.getPower("Focus").amount : 0;

            if (strength > 0) {
                this.addToBot(new ApplyPowerAction(var2, AbstractDungeon.player, new StrengthPower(var2, this.potency * strength), this.potency * strength));
                this.addToBot(new ApplyPowerAction(var2, AbstractDungeon.player, new LoseStrengthPower(var2, this.potency * strength), this.potency * strength));
            }
            if (dexterity > 0) {
                this.addToBot(new ApplyPowerAction(var2, AbstractDungeon.player, new DexterityPower(var2, this.potency * dexterity), this.potency * dexterity));
                this.addToBot(new ApplyPowerAction(var2, AbstractDungeon.player, new LoseDexterityPower(var2, this.potency * dexterity), this.potency * dexterity));
            }
            if (focus > 0) {
                this.addToBot(new ApplyPowerAction(var2, AbstractDungeon.player, new FocusPower(var2, this.potency * focus), this.potency * focus));
                this.addToBot(new ApplyPowerAction(var2, AbstractDungeon.player, new LoseFocusPower(var2, this.potency * focus), this.potency * focus));
            }
        }
    }
}
