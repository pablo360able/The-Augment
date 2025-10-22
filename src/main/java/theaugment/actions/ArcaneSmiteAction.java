package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class ArcaneSmiteAction extends AbstractGameAction {
    private final boolean freeToPlayOnce;
    private final int damage;
    private final AbstractPlayer p;
    private final AbstractMonster m;
    private final DamageInfo.DamageType damageTypeForTurn;
    private final int energyOnUse;

    public ArcaneSmiteAction(AbstractPlayer p, AbstractMonster m, int damage, DamageInfo.DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.m = m;
        this.damage = damage;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.damageTypeForTurn = damageTypeForTurn;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        int focus = this.p.hasPower("Focus") ? this.p.getPower("Focus").amount : 0;
        int strength = this.p.hasPower("Strength") ? this.p.getPower("Strength").amount : 0;

        if (this.energyOnUse != -1) {
            effect = this.energyOnUse + Math.max(focus, 0);
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            for(int i = 0; i < effect; ++i) {
                this.addToBot(new DamageAction(this.m, new DamageInfo(this.p, this.damage + focus - strength, this.damageTypeForTurn), AttackEffect.SLASH_HEAVY));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
