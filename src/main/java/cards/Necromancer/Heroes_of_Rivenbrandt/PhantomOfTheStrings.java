package cards.Necromancer.Heroes_of_Rivenbrandt;

import actions.Destroy_PhantomOfTheStrings;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class PhantomOfTheStrings extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:PhantomOfTheStrings");

    public static final String ID = "SvTS:PhantomOfTheStrings";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Attack/PhantomOfTheStrings.png";
    public static final String UPGRADE_PATH = "img/Necromancer/cards/Attack/PhantomOfTheStrings_Evolved.png";


    private boolean ToDegrade;
    private static final int COST = 1;
    private static final int BASE_BLOCK = 10;
    private static final int UPGRADE_PLUS_BLOCK = 5;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public PhantomOfTheStrings(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = this.block = BASE_BLOCK;

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Heroes_of_Rivenbrandt);
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        if(this.upgraded) this.ToDegrade = true;
        else this.ToDegrade = false;

        addToBot(new Destroy_PhantomOfTheStrings(this));
        addToBot(new GainBlockAction(Player, this.block));
        if(this.upgraded) addToBot(new SFXAction("SvTS:PhantomOfTheStrings_Evolved"));
        else addToBot(new SFXAction("SvTS:PhantomOfTheStrings"));
    }

    @Override
    public void onMoveToDiscard(){
        degrade();
        superFlash();
    }

    @Override
    public AbstractCard makeCopy(){
        return new PhantomOfTheStrings();
    }

    @Override
    public void upgrade(){
        upgradeName();
        this.rawDescription = CARDSTRINGS.UPGRADE_DESCRIPTION;
        initializeDescription();
        upgradeBlock(UPGRADE_PLUS_BLOCK);
        this.textureImg = UPGRADE_PATH;
        loadCardImage(this.textureImg);
        super.upgrade();
    }

    private void degradeName(){
        --this.timesUpgraded;
        this.upgraded = false;
        this.name = NAME;
        initializeTitle();
    }

    private void degradeBlock(){
        this.block = this.baseBlock = BASE_BLOCK;
        this.isBlockModified = false;
        this.upgradedBlock = false;
    }

    private void degrade() {
        if (this.upgraded) {
            degradeName();
            degradeBlock();
            this.rawDescription = CARDSTRINGS.DESCRIPTION;
            initializeDescription();
            this.textureImg = IMG_PATH;
            loadCardImage(this.textureImg);
        }
    }
}
