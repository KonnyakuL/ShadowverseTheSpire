package cards.Neutral;

import actions.SelectCardsToEvolve;
import cards.SvTS_AbstractCard;
import characters.SvTS_AbstractPlayer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EvolutionPoint extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:EvolutionPoint");

    public static final String ID = "SvTS:EvolutionPoint";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Neutral/Status/EvolutionPoint.png";
    public static final String UPGRADE_PATH = "img/Neutral/Status/EvolutionPoint_Evolved.png";


    private static final int COST = -2;
    private static final CardType TYPE = CardType.STATUS;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;



    public EvolutionPoint(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.isEthereal = true;
        this.tags.add(SvTS_Enums.Banish);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        if(p instanceof SvTS_AbstractPlayer){
            if (AbstractDungeon.actionManager.turnHasEnded) {
                this.cantUseMessage = TEXT[9];
                return false;
            }
            else this.cantUseMessage = null;
            return !((SvTS_AbstractPlayer) p).EPUsedInTurn;
        }
        return false;
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        if(Player instanceof SvTS_AbstractPlayer) ((SvTS_AbstractPlayer) Player).EPUsedInTurn = true;
        addToBot(new SelectCardsToEvolve(1));
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            super.upgrade();
            upgradeName();
            this.textureImg = UPGRADE_PATH;
            loadCardImage(this.textureImg);
            this.rawDescription = CARDSTRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
            this.isEthereal = false;
            this.selfRetain = true;
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new EvolutionPoint();
    }
}
