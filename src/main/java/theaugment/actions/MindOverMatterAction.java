package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class MindOverMatterAction extends AbstractGameAction {
    private AbstractPlayer p;

    public MindOverMatterAction() {
        this.actionType = ActionType.WAIT;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST && this.p.hasPower("Strength")) {
            int strAmt = this.p.getPower("Strength").amount;
            this.addToTop(new ApplyPowerAction(this.p, this.p, new FocusPower(this.p, strAmt), strAmt));
            this.addToTop(new ApplyPowerAction(this.p, this.p, new StrengthPower(this.p, -strAmt), -strAmt));
        }

        this.tickDuration();
    }
}
