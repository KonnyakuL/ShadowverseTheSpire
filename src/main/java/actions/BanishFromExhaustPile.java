package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BanishFromExhaustPile extends AbstractGameAction {
    private AbstractCard card;

    public BanishFromExhaustPile(AbstractCard card){
        this.card = card;
    }

    @Override
    public void update(){
        AbstractDungeon.player.exhaustPile.removeCard(this.card);
        this.isDone = true;
    }
}
