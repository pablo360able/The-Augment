package theaugment.relics;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theaugment.character.Augment;

import static theaugment.TheAugmentMod.makeID;

public class Dreidl extends BaseRelic {
    private static final String NAME = Dreidl.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static boolean stillDiscarding = false;

    public Dreidl() {
        super(ID, NAME, Augment.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onManualDiscard() {
        if (!stillDiscarding) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new DrawCardAction(1));
            stillDiscarding = true;
        }
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        stillDiscarding = false;
    }
}
