package theaugment.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theaugment.powers.LoseFocusPower;
import theaugment.util.AugmentEnum;

import static theaugment.TheAugmentMod.makeID;

public class GracePotion extends BasePotion {
    public static final String ID = makeID(GracePotion.class.getSimpleName());

    private static final Color LIQUID_COLOR = Color.valueOf("0d429dff");
    private static final Color HYBRID_COLOR = Color.CYAN.cpy();
    private static final Color SPOTS_COLOR = null;

    public GracePotion() {
        super(ID, 5, PotionRarity.COMMON, PotionSize.EYE, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        playerClass = AugmentEnum.AUGMENT;
    }

    @Override
    public String getDescription() {
        return DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1] + this.potency + DESCRIPTIONS[2];
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        AbstractCreature var2 = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(var2, AbstractDungeon.player, new FocusPower(var2, this.potency), this.potency));
            this.addToBot(new ApplyPowerAction(var2, AbstractDungeon.player, new LoseFocusPower(var2, this.potency), this.potency));
        }
    }
}
