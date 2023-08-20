package cards.Necromancer.Attack;

import actions.SelectCardsToEvolve;
import actions.Waiting;
import cards.SvTS_AbstractCard;
import cards.SvTS_CostChangableAbstractCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class Test extends SvTS_CostChangableAbstractCard {
    //从.json文件中提取键名为 Strike_Necromancer 的信息
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:Test");

    public static final String ID = "SvTS:Test";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Attack/shadowverse.png";
    public static final String ACCELERATE_IMG_PATH = "img/Necromancer/cards/Skill/shadowverse.png";

    private static final int COST = 2;
    private static final int BASE_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int ACCELERATE_COST = 0;
//    private static final int ENHANCE1_COST = 2;
//    private static final int ENHANCE2_COST = 3;
    private static final int BURIAL_NUMBER = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Test(){
        super(ACCELERATE_COST, ACCELERATE_IMG_PATH, ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = BASE_DMG;
        this.tags.add(CardTags.STARTER_STRIKE);

        this.tags.add(SvTS_AbstractCard.SvTS_Enums.Necromancer);
        this.tags.add(SvTS_AbstractCard.SvTS_Enums.Basic);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        if(!AbleToAccelerate()) return super.canUse(p, m);
        int AttackNumber = 0;
        for(AbstractCard card : p.hand.group){
            if(card.type == CardType.ATTACK && card.uuid != this.uuid) AttackNumber++;
        }
        return AttackNumber >= BURIAL_NUMBER && super.canUse(p, m);
    }

    private void Accelerate(){
        this.type = CardType.SKILL;
        this.Accelerate_Status = true;
        this.textureImg = ACCELERATE_IMG_PATH;
        this.cost = ACCELERATE_COST;
        loadCardImage(this.textureImg);
        this.rawDescription = CARDSTRINGS.EXTENDED_DESCRIPTION[0];
        initializeDescription();
        superFlash();

        addToBot(new SelectCardsToEvolve(1));
    }

    @Override
    public void update(){
        if(AbleToAccelerate()) this.target = CardTarget.SELF;
        else this.target = this.BaseTarget;
        super.update();
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        this.isUsedForTurn = true;
        if(AbleToAccelerate()){
            Accelerate();
            return;
        }
        addToBot(new DrawCardAction(1));
        addToTop(new Waiting(1F));
    }

    @Override
    public AbstractCard makeCopy(){
        return new Test();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            super.upgrade();
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
