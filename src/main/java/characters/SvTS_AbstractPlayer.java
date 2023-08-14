package characters;

import actions.BanishFromExhaustPile;
import basemod.abstracts.CustomPlayer;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;

import java.util.ArrayList;

public abstract class SvTS_AbstractPlayer extends CustomPlayer{
    public ArrayList<String> ReanimatedCards = new ArrayList<>();
    public boolean EPUsedInTurn;
    public boolean EvolvedLastTurn;
    public boolean EvolvedInTurn;

    SvTS_AbstractPlayer(String name, AbstractPlayer.PlayerClass playerClass, EnergyOrbInterface energyOrbInterface, String model, String animation){
        super(name, playerClass, energyOrbInterface, model, animation);
    }

    public boolean AbleToReanimate(String ID){
        for(String CardID : ReanimatedCards){
            if(CardID.equals(ID)) return false;
        }
        return true;
    }

    @Override
    public void useCard(AbstractCard c, AbstractMonster monster, int energyOnUse){
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1){
            ReanimatedCards.clear();
        }
        super.useCard(c, monster, energyOnUse);
    }

    @Override
    public void onCardDrawOrDiscard(){
        for(AbstractCard card : this.exhaustPile.group){
            if(card.hasTag(SvTS_AbstractCard.SvTS_Enums.Banish)) AbstractDungeon.actionManager.addToBottom(new BanishFromExhaustPile(card));
        }
        super.onCardDrawOrDiscard();
    }

    @Override
    public void applyStartOfTurnPreDrawCards(){
        this.EvolvedLastTurn = this.EvolvedInTurn;
        this.EvolvedInTurn = false;
        this.EPUsedInTurn = false;
        super.applyStartOfTurnPreDrawCards();
    }
}
