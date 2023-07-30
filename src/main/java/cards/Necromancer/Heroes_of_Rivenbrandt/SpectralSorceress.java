package cards.Necromancer.Heroes_of_Rivenbrandt;

import actions.SvTS_MakeTempCardInHandAction;
import cards.Necromancer.Attack.Ghost;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.Power_SpectralSorceress;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class SpectralSorceress extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:SpectralSorceress");

    public static final String ID = "SvTS:SpectralSorceress";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Attack/SpectralSorceress.png";
    public static final String UPGRADE_PATH = "img/Necromancer/cards/Attack/SpectralSorceress_Evolved.png";


    private static final int COST = 0;
    private static final int BASE_BLOCK = 3;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public SpectralSorceress(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BASE_BLOCK;
        this.cardsToPreview = new Ghost();

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Heroes_of_Rivenbrandt);
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        if(this.upgraded){
            addToBot(new SFXAction("SvTS:SpectralSorceress_Evolved"));
        }
        else{
            addToBot(new SFXAction("SvTS:SpectralSorceress"));
        }
        addToBot(new GainBlockAction(Player, this.block));
        addToBot(new SvTS_MakeTempCardInHandAction(new Ghost()));
        if(!Player.hasPower("SvTS:Power_SpectralSorceress")){
            addToBot(new ApplyPowerAction(Player, Player, new Power_SpectralSorceress(Player)));
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new SpectralSorceress();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.textureImg = UPGRADE_PATH;
            loadCardImage(this.textureImg);
        }
    }
}
