package theaugment.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theaugment.powers.KnowledgeIsPowerPower;

public class UnscheduleKipAction extends AbstractGameAction {
    private KnowledgeIsPowerPower kip;

    public UnscheduleKipAction(KnowledgeIsPowerPower kip) {
        this.kip = kip;
    }

    public void update() {
        if (AbstractDungeon.actionManager.isEmpty()) {
            kip.unschedule();
        } else {
            addToBot(new UnscheduleKipAction(kip));
        }
        this.isDone = true;
    }
}

