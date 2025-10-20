package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theaugment.cards.MagicMissile;

public class MissileSprayAction extends AbstractGameAction {
    private final boolean freeToPlayOnce;
    private final AbstractPlayer p;
    private final int magicNumber;
    private final int energyOnUse;

    public MissileSprayAction(AbstractPlayer p, int magicNumber, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.magicNumber = magicNumber;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;

        if (this.energyOnUse != -1) {
            effect = this.energyOnUse + this.magicNumber;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            for(int i = 0; i < effect; ++i) {
                this.addToBot(new MakeTempCardInHandAction(new MagicMissile()));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
