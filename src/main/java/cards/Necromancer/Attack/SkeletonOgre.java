package cards.Necromancer.Attack;

import actions.Necromancy;
import cards.AbstractCustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class SkeletonOgre extends AbstractCustomCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:SkeletonOgre");

    public static final String ID = "SvTS:SkeletonOgre";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    //-----------------------------TODO------------------------
    public static final String IMG_PATH = "img/Necromancer/cards/Attack/SkeletonOgre.png";


    private static final int COST = 1;
    private static final int BASE_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int NECROMANCY = 4;


    public SkeletonOgre(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = BASE_DMG;
        this.damage = this.baseDamage;
        //-----------------TODO----------------------
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
        addToBot(new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn)));
        if(AbleToNecromancy(Player, NECROMANCY)){
            this.baseDamage += 3;
        }
        addToBot(new Necromancy(AbstractDungeon.player, NECROMANCY, null));
    }

    @Override
    public AbstractCard makeCopy(){
        return new SkeletonOgre();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

}
