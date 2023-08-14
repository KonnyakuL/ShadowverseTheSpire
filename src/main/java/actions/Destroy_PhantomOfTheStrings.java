package actions;

import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import powers.Cemetery;

public class Destroy_PhantomOfTheStrings extends AbstractGameAction {
    private AbstractCard source;

    public Destroy_PhantomOfTheStrings(AbstractCard source){
        this.source = source;
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
            AbstractDungeon.gridSelectScreen.open(temp, 1, String.format(CardCrawlGame.languagePack.getUIString("SvTS:Destroy").TEXT[0], 1), false);
            tickDuration();
            return;
        }
        if(AbstractDungeon.gridSelectScreen.selectedCards.size() != 0){
            for(AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards){
                card.unhover();
                addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.discardPile));
                if(card.cost <= 2 && !card.hasTag(SvTS_AbstractCard.SvTS_Enums.Necromancer)){
                    AbstractCard tmp = card.makeCopy();
                    tmp.setCostForTurn(0);
                    addToBot(new SvTS_MakeTempCardInHandAction(tmp));
                    addToBot(new UpgradeSpecificCardAction(this.source));
                }
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        tickDuration();
    }
}
