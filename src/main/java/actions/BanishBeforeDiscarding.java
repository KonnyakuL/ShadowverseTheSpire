package actions;

import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

public class BanishBeforeDiscarding extends AbstractGameAction {
    private CardGroup pile;

    public BanishBeforeDiscarding(CardGroup pile){
        this.pile = pile;
    }

    @Override
    public void update(){
        for(AbstractCard card : pile.group){
            if(card.hasTag(SvTS_AbstractCard.SvTS_Enums.Banish)){
                addToTop(new ExhaustSpecificCardAction(card, pile));
            }
        }
        this.isDone = true;
    }
}
