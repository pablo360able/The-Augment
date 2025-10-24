package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theaugment.cards.skills.DropConcentration;
import theaugment.cards.skills.MaintainConcentration;

import java.util.ArrayList;

public class ConcentrationAction extends AbstractGameAction {
    private final ArrayList<AbstractCard> choices;

    public ConcentrationAction(AbstractPower power) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.choices = new ArrayList<>();
        this.choices.add(new DropConcentration());
        this.choices.add(new MaintainConcentration());
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.chooseOneOpen(this.choices);
            this.tickDuration();
        } else {
            this.tickDuration();
        }
    }
}