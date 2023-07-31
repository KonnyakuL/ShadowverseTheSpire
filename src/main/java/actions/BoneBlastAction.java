package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BoneBlastAction extends AbstractGameAction {
    private AbstractPlayer Player;
    private AbstractMonster Monster;
    private int Amount;
    private int MagicNumber;
    private DamageInfo.DamageType Type;

    public BoneBlastAction(AbstractPlayer Player, AbstractMonster Monster, DamageInfo.DamageType Type, int MagicNumber){
        this.Player = Player;
        this.Monster = Monster;
        this.MagicNumber = MagicNumber;
        this.Type = Type;
    }

    @Override
    public void update(){
        for(AbstractCard card : this.Player.hand.group){
            if(card.cardID.equals("SvTS:Skeleton")){
                this.Amount++;
            }
        }
        addToBot(new DamageAction(this.Monster, new DamageInfo(this.Player, this.Amount * this.MagicNumber, this.Type), AttackEffect.SLASH_HEAVY));
        this.isDone = true;
    }
}
