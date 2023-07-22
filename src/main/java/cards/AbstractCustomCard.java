package cards;

import actions.GainCemetery;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class AbstractCustomCard extends CustomCard {

    public AbstractCustomCard(String ID, String NAME, String IMG_PATH, int COST, String DESCRIPTION, CardType TYPE, CardColor COLOR, CardRarity RARITY, CardTarget TARGET){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        isUsed = false;
    }

    @Override
    public void onMoveToDiscard(){
        isUsed = false;
    }

    @Override
    public void triggerOnExhaust(){
        if(!isUsed && !this.cardID.equals("SvTS:Ghost")){
            addToBot(new GainCemetery(AbstractDungeon.player, AbstractDungeon.player, 1));
        }
    }
}
