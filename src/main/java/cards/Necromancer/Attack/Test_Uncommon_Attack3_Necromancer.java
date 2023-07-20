package cards.Necromancer.Attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class Test_Uncommon_Attack3_Necromancer extends CustomCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:Test_Uncommon_Attack3_Necromancer");

    public static final String ID = "SvTS:Test_Uncommon_Attack3_Necromancer";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    //-----------------------------TODO------------------------
    public static final String IMG_PATH = "img/Necromancer/cards/Attack/shadowverse.png";


    private static final int COST = 1;
    private static final int BASE_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public Test_Uncommon_Attack3_Necromancer(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = BASE_DMG;
        //-----------------TODO----------------------
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        addToBot(new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn)));
    }

    @Override
    public AbstractCard makeCopy(){
        return new Test_Uncommon_Attack3_Necromancer();
    }

    @Override
    public boolean isStrike(){
        return true;
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}