package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import powers.Cemetery;

public class Destroy extends AbstractGameAction {
    private int number;
    private AbstractGameAction reward;

    public Destroy(int number, AbstractGameAction reward){
        this.number = number;
        this.reward = reward;
        this.actionType = ActionType.EXHAUST;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update(){
        if(!AbstractDungeon.player.hasPower("SvTS:Cemetery")){
            AbstractDungeon.player.powers.add(new Cemetery(AbstractDungeon.player, 0));
        }
        if(this.duration == Settings.ACTION_DUR_MED){
            CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for(AbstractCard card : AbstractDungeon.player.discardPile.group){
                if(CardLibrary.getCard(card.cardID).type == AbstractCard.CardType.ATTACK){
                    temp.addToTop(card);
                }
            }
            if(temp.size() == 0){
                this.isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(temp, this.number, CardCrawlGame.languagePack.getUIString("ExhaustAction").TEXT[0], false);
            tickDuration();
            return;
        }
        if(AbstractDungeon.gridSelectScreen.selectedCards.size() != 0){
            for(AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards){
                card.unhover();
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.discardPile));
                if(this.reward != null){
                    addToBot(this.reward);
                }
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        tickDuration();
    }
}
