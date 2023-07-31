package cards.Necromancer.Attack;

import actions.Reanimate;
import cards.SvTS_AbstractCard;
import cards.SvTS_EnhanceAbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class Test extends SvTS_EnhanceAbstractCard {
    //从.json文件中提取键名为 Strike_Necromancer 的信息
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:Test");

    public static final String ID = "SvTS:Test";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Attack/shadowverse.png";

    private static final int COST = 1;
    private static final int BASE_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int ENHANCE1_COST = 2;
    private static final int ENHANCE2_COST = 3;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Test(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, ENHANCE1_COST, ENHANCE2_COST);
        this.damage = this.baseDamage = BASE_DMG;
        this.tags.add(CardTags.STARTER_STRIKE);

        this.tags.add(SvTS_AbstractCard.SvTS_Enums.Necromancer);
        this.tags.add(SvTS_AbstractCard.SvTS_Enums.Basic);
    }



    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        addToBot(new Reanimate(Player, 6));
    }

    @Override
    public AbstractCard makeCopy(){
        return new Test();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
