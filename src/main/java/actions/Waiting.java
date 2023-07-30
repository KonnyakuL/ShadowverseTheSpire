package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class Waiting extends AbstractGameAction {

    public Waiting(float duration){
        this.duration = duration;
    }

    @Override
    public void update(){
        tickDuration();
    }
}
