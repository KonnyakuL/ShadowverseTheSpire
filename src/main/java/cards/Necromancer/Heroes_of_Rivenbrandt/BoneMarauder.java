package cards.Necromancer.Heroes_of_Rivenbrandt;

import Modifiers.AddExhaustModifier;
import actions.SvTS_MakeTempCardInHandAction;
import basemod.helpers.CardModifierManager;
import cards.Necromancer.Rebirth_of_Glory.BoneBlast;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class BoneMarauder extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:BoneMarauder");

    public static final String ID = "SvTS:BoneMarauder";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Attack/BoneMarauder.png";
    public static final String UPGRADE_PATH = "img/Necromancer/cards/Attack/BoneMarauder_Evolved.png";


    private static final int COST = 2;
    private static final int BASE_BLOCK = 8;
    private static final int UPGRADE_PLUS_BLOCK = 6;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public BoneMarauder(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BASE_BLOCK;
        this.cardsToPreview = new BoneBlast();

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Heroes_of_Rivenbrandt);
    }

    @Override
    public AbstractCard makeCopy(){
        return new BoneMarauder();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.textureImg = UPGRADE_PATH;
            loadCardImage(this.textureImg);
            super.upgrade();
        }
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        this.isUsed = true;
        addToBot(new GainBlockAction(Player, this.block));
        AbstractCard CARD_TO_ADD = new BoneBlast();
        CARD_TO_ADD.costForTurn = 0;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                CardModifierManager.addModifier(CARD_TO_ADD, new AddExhaustModifier(CARD_TO_ADD));
                this.isDone = true;
            }
        });
        if(this.upgraded){
            addToBot(new SFXAction("SvTS:BoneMarauder_Evolved"));
        }
        else{
            addToBot(new SFXAction("SvTS:BoneMarauder"));
        }
        addToBot(new SvTS_MakeTempCardInHandAction(CARD_TO_ADD));
    }
}
