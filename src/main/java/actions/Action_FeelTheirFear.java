package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import powers.Power_FeelTheirFear;

import java.util.ArrayList;

public class Action_FeelTheirFear extends AbstractGameAction {
    private ArrayList<AbstractCard> CardPool = new ArrayList<>();
    
    public Action_FeelTheirFear(AbstractCreature target){
        this.target = target;
    }

    private boolean AbleToNecromancy(AbstractCreature Player, int number){
        if(Player.hasPower("SvTS:Cemetery")){
            for(AbstractPower i : Player.powers){
                if(i.ID.equals("SvTS:Cemetery")){
                    return i.amount >= number;
                }
            }
        }
        return false;
    }

    @Override
    public void update(){
        addToBot(new ApplyPowerAction(this.target, this.target, new Power_FeelTheirFear(this.target, 1)));
        if(AbleToNecromancy(this.target, 8)){
            for(AbstractCard card : AbstractDungeon.player.exhaustPile.group){
                if(card.cost == 2 && card.type == AbstractCard.CardType.ATTACK){
                    CardPool.add(card.makeCopy());
                }
            }
            
            if(!CardPool.isEmpty()){
                AbstractCard tmp = CardPool.get((int)(Math.random() * CardPool.size()));
                tmp.setCostForTurn(Math.max(tmp.costForTurn - 2, 0));
                addToBot(new SvTS_MakeTempCardInHandAction(tmp));
            }
        }
        this.isDone = true;
    }
}