package cards.Necromancer.Skill;

import actions.Necromancy;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class FoulTempest extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:FoulTempest");

    public static final String ID = "SvTS:FoulTempest";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Skill/FoulTempest.png";


    private static final int COST = 1;
    private static final int BASE_DMG = 3;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int NECROMANCY = 6;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public FoulTempest(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = BASE_DMG;
        this.magicNumber = this.baseMagicNumber = 3 * this.damage;

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Classic);
    }

    private boolean AbleToNecromancy(AbstractPlayer Player, int number){
        if(Player.hasPower("SvTS:Cemetery")){
            for(AbstractPower i : Player.powers){
                if(i.ID.equals("SvTS:Cemetery")){
                    return i.amount >= number;
                }
            }
        }
        return false;
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        if(AbleToNecromancy(Player, NECROMANCY)){

            addToBot(new Necromancy(Player, NECROMANCY, new DamageAllEnemiesAction(Player, this.magicNumber, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL)));
        }
        else{
            addToBot(new DamageAllEnemiesAction(Player, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(3 * UPGRADE_PLUS_DMG);
            super.upgrade();
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new FoulTempest();
    }
}
