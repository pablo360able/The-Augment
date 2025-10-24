package theaugment.relics;


import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import theaugment.cards.attacks.BackStrike;
import theaugment.cards.attacks.SnapBack;
import theaugment.cards.skills.Backstep;
import theaugment.cards.skills.BackupPlan;
import theaugment.cards.skills.CircleBack;
import theaugment.cards.skills.DoubleBack;
import theaugment.character.Augment;

import java.util.ArrayList;

import static theaugment.TheAugmentMod.makeID;

public class Backpack extends BaseRelic {
    private static final String NAME = Backpack.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public Backpack() {
        super(ID, NAME, Augment.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip() {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        AbstractCard backstep = new Backstep();
        UnlockTracker.markCardAsSeen(backstep.cardID);
        group.addToBottom(backstep.makeCopy());

        AbstractCard backStrike = new BackStrike();
        UnlockTracker.markCardAsSeen(backStrike.cardID);
        group.addToBottom(backStrike.makeCopy());

        AbstractCard backupPlan = new BackupPlan();
        UnlockTracker.markCardAsSeen(backupPlan.cardID);
        group.addToBottom(backupPlan.makeCopy());

        AbstractCard snapBack = new SnapBack();
        UnlockTracker.markCardAsSeen(snapBack.cardID);
        group.addToBottom(snapBack.makeCopy());

        AbstractCard circleBack = new CircleBack();
        UnlockTracker.markCardAsSeen(circleBack.cardID);
        group.addToBottom(circleBack.makeCopy());

        AbstractCard doubleBack = new DoubleBack();
        UnlockTracker.markCardAsSeen(doubleBack.cardID);
        group.addToBottom(doubleBack.makeCopy());

        AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, this.DESCRIPTIONS[1]);
        CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
