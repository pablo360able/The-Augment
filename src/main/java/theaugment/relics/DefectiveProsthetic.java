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

    private void blueCards() {

        AbstractPlayer p = AbstractDungeon.player;

        ArrayList<AbstractCard> tmpPool = new ArrayList<>();
        p.getCardPool(tmpPool);
        CardLibrary.addBlueCards(tmpPool);

        AbstractDungeon.commonCardPool.clear();
        AbstractDungeon.uncommonCardPool.clear();
        AbstractDungeon.rareCardPool.clear();

        for(AbstractCard c : tmpPool) {
            switch (c.rarity) {
                case COMMON:
                    AbstractDungeon.commonCardPool.addToTop(c);
                    break;
                case UNCOMMON:
                    AbstractDungeon.uncommonCardPool.addToTop(c);
                    break;
                case RARE:
                    AbstractDungeon.rareCardPool.addToTop(c);
                    break;
                default:
                    continue;
            }
        }

        AbstractDungeon.srcRareCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        AbstractDungeon.srcUncommonCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        AbstractDungeon.srcCommonCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);



        for(AbstractCard c : AbstractDungeon.rareCardPool.group) {
            AbstractDungeon.srcRareCardPool.addToBottom(c);
        }

        for(AbstractCard c : AbstractDungeon.uncommonCardPool.group) {
            AbstractDungeon.srcUncommonCardPool.addToBottom(c);
        }

        for(AbstractCard c : AbstractDungeon.commonCardPool.group) {
            AbstractDungeon.srcCommonCardPool.addToBottom(c);
        }
    }
}
