package cards.Necromancer.Base;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class ElderSpartoiSoldier extends CustomCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:ElderSpartoiSoldier");

    public static final String ID = "SvTS:ElderSpartoiSoldier";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    //-----------------------------TODO------------------------
    public static final String IMG_PATH = "img/Necromancer/cards/Base/ElderSpartoiSoldier.png";


    private static final int COST = 2;
    private static final int BASE_DMG = 13;
    private static final int UPGRADE_PLUS_DMG = 6;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public ElderSpartoiSoldier(){
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
        return new ElderSpartoiSoldier();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
