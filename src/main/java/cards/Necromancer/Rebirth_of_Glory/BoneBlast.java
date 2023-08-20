package cards.Necromancer.Rebirth_of_Glory;

import actions.BoneBlastAction;
import actions.SvTS_MakeTempCardInHandAction;
import cards.Necromancer.Token.Skeleton;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class BoneBlast extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:BoneBlast");

    public static final String ID = "SvTS:BoneBlast";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Skill/BoneBlast.png";


    private static final int COST = 2;
    private static final int BASE_MAGICNUMBER = 2;
    private static final int UPGRADE_PLUS_MAGICNUMBER = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public BoneBlast(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new Skeleton();
        this.magicNumber = this.baseMagicNumber = BASE_MAGICNUMBER;

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Rebirth_of_Glory);
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        AbstractCard CARD_TO_ADD = new Skeleton();
        if(this.upgraded){
            CARD_TO_ADD.upgrade();
        }
        addToBot(new SvTS_MakeTempCardInHandAction(CARD_TO_ADD, 3));
        addToBot(new BoneBlastAction(Player, Monster, this.damageTypeForTurn, this.magicNumber));
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            super.upgrade();
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGICNUMBER);
            this.cardsToPreview.upgrade();
            this.rawDescription = CARDSTRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new BoneBlast();
    }
}
