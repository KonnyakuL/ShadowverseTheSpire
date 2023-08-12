package actions;

import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;

import java.util.ArrayList;

public class Burial extends AbstractGameAction {
    private int number;
    private AbstractGameAction reward;
    private ArrayList<AbstractCard> TmpToRemove = new ArrayList<>();
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("SvTS:Burial")).TEXT;

    public Burial(int number, AbstractGameAction reward){
        this.number = number;
        this.reward = reward;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update(){
        if(this.duration == Settings.ACTION_DUR_MED){
            if(AbstractDungeon.player.hand.group.size() == 0){
                this.isDone = true;
                return;
            }

            for(AbstractCard card : AbstractDungeon.player.hand.group){
                if(CardLibrary.getCard(card.cardID).type != AbstractCard.CardType.ATTACK){
                    this.TmpToRemove.add(card);
                }
            }

            if(this.TmpToRemove.size() == AbstractDungeon.player.hand.size()){
                this.isDone = true;
                return;
            }
            AbstractDungeon.player.hand.group.removeAll(this.TmpToRemove);
            AbstractDungeon.handCardSelectScreen.open(String.format(CardCrawlGame.languagePack.getUIString("SvTS:Burial").TEXT[0], this.number), this.number, false);
            tickDuration();
            return;
        }

        if(!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved){
            for(AbstractCard card : AbstractDungeon.handCardSelectScreen.selectedCards.group){
                if(card instanceof SvTS_AbstractCard){
                    ((SvTS_AbstractCard) card).isBuried = true;
                }
                AbstractDungeon.effectList.add(new ExhaustCardEffect(card));
                AbstractDungeon.player.hand.moveToExhaustPile(card);
            }
            for(AbstractCard card : this.TmpToRemove){
                AbstractDungeon.player.hand.addToTop(card);
            }
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            if(this.reward != null){
                addToBot(this.reward);
            }
            this.isDone = true;
        }
        tickDuration();
    }
}
