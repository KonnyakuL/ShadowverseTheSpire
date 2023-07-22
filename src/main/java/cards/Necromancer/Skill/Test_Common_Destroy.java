package cards.Necromancer.Skill;

import actions.Destroy;
import cards.AbstractCustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class Test_Common_Destroy extends AbstractCustomCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:Test_Common_Destroy");

    public static final String ID = "SvTS:Test_Common_Destroy";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    //------------------TODO---------------
    public static final String IMG_PATH = "img/Necromancer/cards/Skill/shadowverse.png";


    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Test_Common_Destroy(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //------------------TODO---------------
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        addToBot(new Destroy(1, new DrawCardAction(1)));
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new Test_Common_Destroy();
    }
}
