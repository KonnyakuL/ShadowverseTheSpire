package cards.Necromancer.Heroes_of_Rivenbrandt;

import actions.Destroy;
import actions.SvTS_MakeTempCardInHandAction;
import actions.Waiting;
import cards.Necromancer.Token.Skeleton;
import cards.SvTS_CostChangableAbstractCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.Power_SkullPredator;
import powers.Power_SkullPredator_Evolved;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class SkullPredator extends SvTS_CostChangableAbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:SkullPredator");

    public static final String ID = "SvTS:SkullPredator";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Power/SkullPredator.png";
    public static final String UPGRADE_PATH = "img/Necromancer/cards/Power/SkullPredator_Evolved.png";


    private static final int COST = 1;
    private static final int ENHANCE1_COST = 2;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public SkullPredator(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, ENHANCE1_COST);
        this.cardsToPreview = new Skeleton();

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Heroes_of_Rivenbrandt);
    }

    private void Enhance1(AbstractPlayer Player){
        boolean AbleToDestroy = false;
        for(AbstractCard card : Player.discardPile.group){
            if(CardLibrary.getCard(card.cardID).type == CardType.ATTACK){
                AbleToDestroy = true;
                break;
            }
        }
        if(AbleToDestroy){
            addToBot(new Destroy(1, null));
            upgrade();
            superFlash();
            addToTop(new Waiting(0.5F));
            if(!Player.hasPower("SvTS:Power_SkullPredator_Evolved")) addToBot(new ApplyPowerAction(Player, Player, new Power_SkullPredator_Evolved(Player)));
        }
        addToBot(new SFXAction("SvTS:SkullPredator_Enhance"));
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        addToBot(new SvTS_MakeTempCardInHandAction(new Skeleton()));
        if(!Player.hasPower("SvTS:Power_SkullPredator")) addToBot(new ApplyPowerAction(Player, Player, new Power_SkullPredator(Player)));

        if(this.upgraded){
            addToTop(new SFXAction("SvTS:SkullPredator_Evolved"));
            if(!Player.hasPower("SvTS:Power_SkullPredator_Evolved")) addToBot(new ApplyPowerAction(Player, Player, new Power_SkullPredator_Evolved(Player)));
        }
        else{
            if(GetEnhanceLevel() == 0) addToTop(new SFXAction("SvTS:SkullPredator"));
            else {
                Enhance1(Player);
            }
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new SkullPredator();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            super.upgrade();
            upgradeName();
            this.Enhance1_cost = -1;
            this.rawDescription = CARDSTRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
            this.textureImg = UPGRADE_PATH;
            loadCardImage(this.textureImg);
        }
    }
}
