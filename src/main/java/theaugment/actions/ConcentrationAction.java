package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.cards.skills.DropConcentration;
import theaugment.cards.skills.MaintainConcentration;
import theaugment.powers.ConcentrationPower;

import java.util.ArrayList;

public class ConcentrationAction extends AbstractGameAction {
    private final ArrayList<AbstractCard> choices;

    public ConcentrationAction(ConcentrationPower power) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.choices = new ArrayList<>();
        this.choices.add(new DropConcentration());
        this.choices.add(new MaintainConcentration(power.cardImage));
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