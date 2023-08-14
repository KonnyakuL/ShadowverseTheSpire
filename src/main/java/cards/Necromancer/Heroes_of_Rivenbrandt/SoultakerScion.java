package cards.Necromancer.Heroes_of_Rivenbrandt;

import actions.SvTS_MakeTempCardInHandAction;
import cards.Neutral.EvolutionPoint;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import powers.Power_SoultakerScion;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class SoultakerScion extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:SoultakerScion");

    public static final String ID = "SvTS:SoultakerScion";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Power/SoultakerScion.png";
    public static final String UPDATE_PATH = "img/Necromancer/cards/Power/SoultakerScion_Evolved.png";


    private static final int COST = 2;
    private static final int NECROMANCY = 6;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public SoultakerScion(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new EvolutionPoint();

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Heroes_of_Rivenbrandt);
    }

    private boolean AbleToNecromancy(AbstractPlayer Player, int number){
        if(Player.hasPower("SvTS:Cemetery")){
            for(AbstractPower i : Player.powers){
                if(i.ID.equals("SvTS:Cemetery")){
                    return i.amount >= number;
                }
            }
        }
        return false;
    }


    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        if(this.upgraded) addToBot(new SFXAction("SvTS:SoultakerScion_Evolved"));
        else addToBot(new SFXAction("SvTS:SoultakerScion"));
        if(!Player.hasPower("SvTS:Power_SoultakerScion")){
            addToBot(new ApplyPowerAction(Player, Player, new Power_SoultakerScion(Player)));
        }
        if(AbleToNecromancy(Player, NECROMANCY)){
            addToBot(new SvTS_MakeTempCardInHandAction(new EvolutionPoint()));
        }
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBaseCost(2);
            super.upgrade();
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new SoultakerScion();
    }
}
