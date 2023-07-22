package cards.Necromancer.Base;

import actions.Necromancy;
import cards.AbstractCustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class UndyingResentment extends AbstractCustomCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:UndyingResentment");

    public static final String ID = "SvTS:UndyingResentment";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    //------------------TODO---------------
    public static final String IMG_PATH = "img/Necromancer/cards/Base/UndyingResentment.png";


    private static final int COST = 1;
    private static final int BASE_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int NECROMANCY = 4;
    private static final int NECROMANCY_DMG = 4;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public UndyingResentment(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = BASE_DMG;

        //------------------TODO---------------
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        addToBot(new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new Necromancy(Player, NECROMANCY, new DamageAction(Monster, new DamageInfo(Player, NECROMANCY_DMG, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL)));
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new UndyingResentment();
    }
}
