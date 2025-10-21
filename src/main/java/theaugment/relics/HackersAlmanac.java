package theaugment.relics;


import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theaugment.character.Augment;

import static theaugment.TheAugmentMod.makeID;

public class HackersAlmanac extends BaseRelic {
    private static final String NAME = HackersAlmanac.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public HackersAlmanac() {
        super(ID, NAME, Augment.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        boolean isBoss = false;

        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                isBoss = true;
                break;
            }
        }

        if (!isBoss) {
            this.beginLongPulse();
            this.flash();
        }
    }

    @Override
    public int changeNumberOfCardsInReward(int numberOfCards) {
        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                return numberOfCards;
            }
        }
        this.stopPulse();

        if (AbstractDungeon.getCurrRoom().eliteTrigger) {
            return numberOfCards + 1;
        }
        else {
            return numberOfCards + 2;
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(SourceCode.ID);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new HackersAlmanac();
    }
}
