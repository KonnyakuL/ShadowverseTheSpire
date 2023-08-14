package actions;

import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import powers.Power_FeelTheirFear;

import java.util.ArrayList;

public class Action_CharonStygianFerrywoman extends AbstractGameAction {
    private ArrayList<AbstractCard> CardPool = new ArrayList<>();

    public Action_CharonStygianFerrywoman(AbstractCreature target){
        this.target = target;
    }

    @Override
    public void update(){
        for(AbstractCard card : AbstractDungeon.player.drawPile.group){
            if(card.cost == 3 && (card.type == AbstractCard.CardType.ATTACK || card.type == AbstractCard.CardType.POWER) && card.hasTag(SvTS_AbstractCard.SvTS_Enums.Necromancer)){
                CardPool.add(card);
            }
        }
        if(!CardPool.isEmpty()){
            AbstractCard tmp = CardPool.get((int)(Math.random() * CardPool.size()));
            if (AbstractDungeon.player.hand.size() == 10) {
                AbstractDungeon.player.drawPile.moveToDiscardPile(tmp);
                AbstractDungeon.player.createHandIsFullDialog();
            } else {
                tmp.unhover();
                tmp.lighten(true);
                tmp.setAngle(0.0F);
                tmp.drawScale = 0.12F;
                tmp.targetDrawScale = 0.75F;
                tmp.current_x = CardGroup.DRAW_PILE_X;
                tmp.current_y = CardGroup.DRAW_PILE_Y;
                AbstractDungeon.player.drawPile.removeCard(tmp);
                AbstractDungeon.player.hand.addToTop(tmp);
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();
            }
        }
        this.isDone = true;
    }
}