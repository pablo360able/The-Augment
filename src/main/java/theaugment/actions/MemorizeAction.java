package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import theaugment.cards.skills.Memorize;

import java.util.UUID;

public class MemorizeAction extends AbstractGameAction {
    private int scryIncrease;
    private UUID uuid;

    public MemorizeAction(UUID targetUUID, int miscIncrease) {
        this.scryIncrease = miscIncrease;
        this.uuid = targetUUID;
    }

    public void update() {
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.uuid.equals(this.uuid)) {
                ((Memorize)c).learn();
                c.applyPowers();
            }
        }

        for(AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
            ((Memorize)c).learn();
            c.applyPowers();
        }

        this.isDone = true;
    }
}
