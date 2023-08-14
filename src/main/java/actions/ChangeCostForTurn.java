package actions;

import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;

public class ChangeCostForTurn extends AbstractGameAction {
    private ArrayList<AbstractCard> TmpToRemove = new ArrayList<>();
    private int number;
    private int ChangeCost;

    public ChangeCostForTurn(int number, int ChangeCost){
        this.number = number;
        this.ChangeCost = ChangeCost;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        if(this.duration == Settings.ACTION_DUR_MED){
            if(AbstractDungeon.player.hand.group.size() == 0){
                this.isDone = true;
                return;
            }

            for(AbstractCard card : AbstractDungeon.player.hand.group){
                if(!card.hasTag(SvTS_AbstractCard.SvTS_Enums.Necromancer) || (CardLibrary.getCard(card.cardID).type != AbstractCard.CardType.ATTACK && CardLibrary.getCard(card.cardID).type != AbstractCard.CardType.POWER)){
                    this.TmpToRemove.add(card);
                }
            }

            if(this.TmpToRemove.size() == AbstractDungeon.player.hand.size()){
                this.isDone = true;
                return;
            }
            AbstractDungeon.player.hand.group.removeAll(this.TmpToRemove);
            AbstractDungeon.handCardSelectScreen.open(CardCrawlGame.languagePack.getUIString("SvTS:Action_CharonStygianFerrywoman_Evolving").TEXT[0], this.number, false);
            tickDuration();
            return;
        }

        if(!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved){
            for(AbstractCard card : AbstractDungeon.handCardSelectScreen.selectedCards.group){
                AbstractDungeon.player.hand.addToTop(card);
                card.setCostForTurn(Math.max(card.costForTurn - this.ChangeCost, 0));
            }
            for(AbstractCard card : this.TmpToRemove){
                AbstractDungeon.player.hand.addToTop(card);
            }
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        tickDuration();
    }
}