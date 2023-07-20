package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import powers.Cemetery;

public class GainCemetery extends AbstractGameAction {
    public GainCemetery(AbstractCreature target, AbstractCreature source, int number){
        this.target = target;
        this.source = source;
        this.amount = number;
    }

    @Override
    public void update(){
        if(!this.target.hasPower("SvTS:Cemetery")){
            this.target.powers.add(new Cemetery(this.target, 0));
        }
        addToBot(new ApplyPowerAction(this.target, this.source, new Cemetery(this.target, this.amount)));
        this.isDone = true;
    }
}
