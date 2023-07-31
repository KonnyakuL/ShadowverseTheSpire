package cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public abstract class SvTS_EnhanceAbstractCard extends SvTS_AbstractCard {
    protected int Enhance1_cost;
    protected int Enhance2_cost;
    protected int Enhance3_cost;
    protected int Enhance4_cost;
    protected int Enhance5_cost;
    private int EnhanceLevel;
    private boolean isInHand;

    public SvTS_EnhanceAbstractCard(String ID, String NAME, String IMG_PATH, int COST, String DESCRIPTION, CardType TYPE, CardColor COLOR, CardRarity RARITY, CardTarget TARGET){
        this(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, -1, -1, -1, -1, -1);
    }

    public SvTS_EnhanceAbstractCard(String ID, String NAME, String IMG_PATH, int COST, String DESCRIPTION, CardType TYPE, CardColor COLOR, CardRarity RARITY, CardTarget TARGET, int Enhance1_cost){
        this(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, Enhance1_cost, -1, -1, -1, -1);
    }

    public SvTS_EnhanceAbstractCard(String ID, String NAME, String IMG_PATH, int COST, String DESCRIPTION, CardType TYPE, CardColor COLOR, CardRarity RARITY, CardTarget TARGET, int Enhance1_cost, int Enhance2_cost){
        this(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, Enhance1_cost, Enhance2_cost, -1, -1, -1);
    }

    public SvTS_EnhanceAbstractCard(String ID, String NAME, String IMG_PATH, int COST, String DESCRIPTION, CardType TYPE, CardColor COLOR, CardRarity RARITY, CardTarget TARGET, int Enhance1_cost, int Enhance2_cost, int Enhance3_cost){
        this(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, Enhance1_cost, Enhance2_cost, Enhance3_cost, -1, -1);
    }

    public SvTS_EnhanceAbstractCard(String ID, String NAME, String IMG_PATH, int COST, String DESCRIPTION, CardType TYPE, CardColor COLOR, CardRarity RARITY, CardTarget TARGET, int Enhance1_cost, int Enhance2_cost, int Enhance3_cost, int Enhance4_cost){
        this(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, Enhance1_cost, Enhance2_cost, Enhance3_cost, Enhance4_cost, -1);
    }

    public SvTS_EnhanceAbstractCard(String ID, String NAME, String IMG_PATH, int COST, String DESCRIPTION, CardType TYPE, CardColor COLOR, CardRarity RARITY, CardTarget TARGET, int Enhance1_cost, int Enhance2_cost, int Enhance3_cost, int Enhance4_cost, int Enhance5_cost){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.Enhance1_cost = Enhance1_cost;
        this.Enhance2_cost = Enhance2_cost;
        this.Enhance3_cost = Enhance3_cost;
        this.Enhance4_cost = Enhance4_cost;
        this.Enhance5_cost = Enhance5_cost;
        this.EnhanceLevel = 0;
        this.isInHand = false;
    }

    protected int GetEnhanceLevel(){
        if(Enhance5_cost != -1 && EnergyPanel.getCurrentEnergy() >= Enhance5_cost){
            return 5;
        }
        if(Enhance4_cost != -1 && EnergyPanel.getCurrentEnergy() >= Enhance4_cost){
            return 4;
        }
        if(Enhance3_cost != -1 && EnergyPanel.getCurrentEnergy() >= Enhance3_cost){
            return 3;
        }
        if(Enhance2_cost != -1 && EnergyPanel.getCurrentEnergy() >= Enhance2_cost){
            return 2;
        }
        if(Enhance1_cost != -1 && EnergyPanel.getCurrentEnergy() >= Enhance1_cost){
            return 1;
        }
        return 0;
    }

    @Override
    public SvTS_AbstractCard makeStatEquivalentCopy(){
        SvTS_AbstractCard tmp = (SvTS_AbstractCard)super.makeStatEquivalentCopy();
        tmp.costForTurn = this.costForTurn;
        tmp.BaseCost = this.BaseCost;
        return tmp;
    }

    @Override
    public void triggerOnGlowCheck(){
        if(GetEnhanceLevel() > 0){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else{
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void triggerWhenDrawn(){
        this.isInHand = true;
    }

    @Override
    public void triggerOnExhaust(){
        this.isInHand = false;
    }

    @Override
    public void onMoveToDiscard(){
        this.isInHand = false;
    }

    @Override
    public void triggerWhenCopied(){
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            for(AbstractCard card : AbstractDungeon.player.hand.group){
                if(card.uuid == this.uuid){
                    this.isInHand = true;
                    return;
                }
            }
            this.isInHand = false;
        }
        else this.isInHand = false;
    }

    @Override
    public void update(){
        super.update();
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            if(!this.isInHand){
                if(this.costForTurn != this.BaseCost){
                    this.EnhanceLevel = 0;
                    setCostForTurn(this.BaseCost);
                }
                return;
            }
            if(GetEnhanceLevel() == 5){
                if(this.EnhanceLevel != 5){
                    this.EnhanceLevel = 5;
                    setCostForTurn(Enhance5_cost);
                }
            }
            if(GetEnhanceLevel() == 4){
                if(this.EnhanceLevel != 4){
                    this.EnhanceLevel = 4;
                    setCostForTurn(Enhance4_cost);
                }
            }
            if(GetEnhanceLevel() == 3){
                if(this.EnhanceLevel != 3){
                    this.EnhanceLevel = 3;
                    setCostForTurn(Enhance3_cost);
                }
            }
            if(GetEnhanceLevel() == 2){
                if(this.EnhanceLevel != 2){
                    this.EnhanceLevel = 2;
                    setCostForTurn(Enhance2_cost);
                }
            }
            if(GetEnhanceLevel() == 1){
                if(this.EnhanceLevel != 1){
                    this.EnhanceLevel = 1;
                    setCostForTurn(Enhance1_cost);
                }
            }
            if(GetEnhanceLevel() == 0){
                if(this.EnhanceLevel != 0){
                    this.EnhanceLevel = 0;
                    setCostForTurn(this.BaseCost);
                }
            }
        }
    }
}
