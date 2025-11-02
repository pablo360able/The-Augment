package theaugment.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theaugment.actions.AetherVialAction;
import theaugment.util.AugmentEnum;

import static theaugment.TheAugmentMod.makeID;

public class AetherVial extends BasePotion {
    public static final String ID = makeID(AetherVial.class.getSimpleName());

    private static final Color LIQUID_COLOR = Color.GOLD.cpy();
    private static final Color HYBRID_COLOR = Color.ORANGE.cpy();
    private static final Color SPOTS_COLOR = Color.YELLOW.cpy();

    public AetherVial() {
        super(ID, 1, PotionRarity.UNCOMMON, PotionSize.SPHERE, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        playerClass = AugmentEnum.AUGMENT;
    }

    @Override
    public String getDescription() {
        return DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new AetherVialAction(this.potency));
        }
    }
}
