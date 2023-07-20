package cards.Necromancer.Skill;

import actions.Necromancy;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class SkeletonViper extends CustomCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:SkeletonViper");

    public static final String ID = "SvTS:SkeletonViper";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    //------------------TODO---------------
    public static final String IMG_PATH = "img/Necromancer/cards/Skill/SkeletonViper.png";


    private static final int COST = 1;
    private static final int BASE_BLOCK = 4;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int NECROMANCY = 4;
    private static final int NECROMANCY_BLOCK = 4;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public SkeletonViper(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BASE_BLOCK;

        //------------------TODO---------------
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        addToBot(new GainBlockAction(Player, Player, this.block));
        addToBot(new Necromancy(Player, NECROMANCY, new GainBlockAction(Player, Player, NECROMANCY_BLOCK)));
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new SkeletonViper();
    }
}
