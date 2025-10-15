package theaugment.relics;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import theaugment.character.Augment;

import java.util.ArrayList;

import static theaugment.TheAugmentMod.makeID;

public class DefectiveProsthetic extends BaseRelic {
    private static final String NAME = DefectiveProsthetic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.SHOP;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public DefectiveProsthetic() {
        super(ID, NAME, Augment.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.masterMaxOrbs++;
        this.blueCards();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void blueCards() {

        AbstractPlayer p = AbstractDungeon.player;

        ArrayList<AbstractCard> tmpPool = new ArrayList<>();
        CardLibrary.addBlueCards(tmpPool);

        for(AbstractCard c : tmpPool) {
            switch (c.rarity) {
                case COMMON:
                    AbstractDungeon.commonCardPool.removeCard(c);
                    AbstractDungeon.srcCommonCardPool.removeCard(c);
                    AbstractDungeon.commonCardPool.addToTop(c);
                    AbstractDungeon.srcCommonCardPool.addToBottom(c);
                    break;
                case UNCOMMON:
                    AbstractDungeon.uncommonCardPool.removeCard(c);
                    AbstractDungeon.srcUncommonCardPool.removeCard(c);
                    AbstractDungeon.uncommonCardPool.addToTop(c);
                    AbstractDungeon.srcUncommonCardPool.addToBottom(c);
                    break;
                case RARE:
                    AbstractDungeon.rareCardPool.removeCard(c);
                    AbstractDungeon.srcRareCardPool.removeCard(c);
                    AbstractDungeon.rareCardPool.addToTop(c);
                    AbstractDungeon.srcRareCardPool.addToBottom(c);
                    break;
                default:
                    continue;
            }
        }
    }
}
