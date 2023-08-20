package actions;

import cards.Necromancer.Token.Ghost;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PhantomHowlAction extends AbstractGameAction {
    private AbstractPlayer Player;
    private int NECROMANCY;

    private int GetNecromancy(){
        if(this.Player.hasPower("SvTS:Cemetery")){
            for(AbstractPower i : this.Player.powers){
                if(i.ID.equals("SvTS:Cemetery")){
                    return i.amount;
                }
            }
        }
        return 0;
    }

    public PhantomHowlAction(AbstractPlayer Player, int NECROMANCY){
        this.Player = Player;
        this.NECROMANCY = NECROMANCY;
    }

    @Override
    public void update(){
        if(GetNecromancy() > 0 && this.Player.hand.size() < 10){
            addToBot(new Necromancy(this.Player, this.NECROMANCY, new SvTS_MakeTempCardInHandAction(new Ghost())));
            addToBot(new PhantomHowlAction(this.Player, this.NECROMANCY));
        }
        this.isDone = true;
    }


}
