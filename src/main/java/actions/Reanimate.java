package actions;

import Modifiers.DoubleDamageForTurn;
import basemod.helpers.CardModifierManager;
import cards.SvTS_AbstractCard;
import characters.SvTS_AbstractPlayer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import java.util.ArrayList;

public class Reanimate extends AbstractGameAction {
    private AbstractPlayer Player;
    private int amount;
    private boolean DoubleDamage;
    private ArrayList<AbstractCard> ReanimatePool = new ArrayList<>();

    public Reanimate(AbstractPlayer Player, int amount){
        this(Player, amount, false);
    }

    public Reanimate(AbstractPlayer Player, int amount, boolean DoubleDamage){
        this.Player = Player;
        this.amount = amount;
        this.DoubleDamage = DoubleDamage;
    }

    @Override
    public void update(){
        if(this.Player instanceof SvTS_AbstractPlayer){
            while(this.amount >= 0){
                if(this.Player.exhaustPile.isEmpty()){
                    break;
                }
                for(AbstractCard card : this.Player.exhaustPile.group){
                    if(!card.hasTag(SvTS_AbstractCard.SvTS_Enums.Banish) && card.type == AbstractCard.CardType.ATTACK && card.cost == this.amount && ((SvTS_AbstractPlayer) this.Player).AbleToReanimate(card.cardID)){
                        ReanimatePool.add(card.makeCopy());
                    }
                }
                if(!ReanimatePool.isEmpty()){
                    AbstractCard tmp = ReanimatePool.get((int)(Math.random() * ReanimatePool.size()));
                    if(tmp.costForTurn != 0){
                        tmp.costForTurn = 0;
                        tmp.isCostModifiedForTurn = true;
                    }
                    if(DoubleDamage){
                        addToBot(new AbstractGameAction() {
                            @Override
                            public void update() {
                                CardModifierManager.addModifier(tmp, new DoubleDamageForTurn(tmp));
                                this.isDone = true;
                            }
                        });
                    }
                    addToBot(new SvTS_MakeTempCardInHandAction(tmp));
                    ((SvTS_AbstractPlayer) this.Player).ReanimatedCards.add(tmp.cardID);
                    break;
                }
                this.amount--;
            }
        }
        this.isDone = true;
    }
}
