package actions;

import cards.Necromancer.Attack.Ghost;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import powers.Cemetery;

public class Necromancy extends AbstractGameAction {
    private final AbstractGameAction action;
    private int necromance = 0;
    public Necromancy(AbstractCreature target, int number, AbstractGameAction action){
        this.target = target;
        this.amount = number;
        this.action = action;
    }

    @Override
    public void update(){
        if(this.target.hasPower("SvTS:Cemetery")){
            for(AbstractPower i : this.target.powers){
                if(i.ID.equals("SvTS:Cemetery")){
                    necromance = i.amount;
                }
            }
        }

        if(necromance >= this.amount){
            addToBot(new ApplyPowerAction(this.target, this.target, new Cemetery(this.target, -this.amount)));
            addToBot(this.action);
            if(this.target.hasPower("SvTS:Power_GhoulsBanquet")){
                addToBot(new MakeTempCardInHandAction(new Ghost()));
            }
        }

        this.isDone = true;
    }
}
