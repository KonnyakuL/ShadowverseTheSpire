package characters;

import basemod.abstracts.CustomPlayer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;

import java.util.ArrayList;

public abstract class SvTS_AbstractPlayer extends CustomPlayer{
    public ArrayList<String> ReanimatedCards = new ArrayList<>();

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

}
