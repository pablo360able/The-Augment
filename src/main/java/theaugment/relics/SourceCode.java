package theaugment.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theaugment.character.Augment;

import static theaugment.TheAugmentMod.makeID;

public class SourceCode extends BaseRelic {
    private static final String NAME = SourceCode.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public SourceCode() {
        super(ID, NAME, Augment.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        boolean isEliteOrBoss = AbstractDungeon.getCurrRoom().eliteTrigger;

        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                isEliteOrBoss = true;
                break;
            }
        }

        if (!isEliteOrBoss) {
            this.beginLongPulse();
            this.flash();
        }
    }

    @Override
    public int changeNumberOfCardsInReward(int numberOfCards) {
        boolean isEliteOrBoss = AbstractDungeon.getCurrRoom().eliteTrigger;

        if (AbstractDungeon.getMonsters() == null) {
            return numberOfCards;
        }

        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                isEliteOrBoss = true;
                break;
            }
        }

        if (!isEliteOrBoss) {
            this.stopPulse();
            return numberOfCards + 1;
        }
        else {
            return numberOfCards;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
