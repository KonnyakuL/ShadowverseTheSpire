package cards.Necromancer.Heroes_of_Rivenbrandt;

import actions.Necromancy;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class HellfireStrike extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:HellfireStrike");

    public static final String ID = "SvTS:HellfireStrike";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Skill/HellfireStrike.png";


    private static final int COST = 0;
    private static final int BASE_DMG = 4;
    private static final int BASE_MAGICNUMBER = 6;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int UPGRADE_PLUS_MAGICNUMBER = 3;
    private static final int NECROMANCY = 4;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public HellfireStrike(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = BASE_DMG;
        this.magicNumber = this.baseMagicNumber = BASE_MAGICNUMBER;

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Heroes_of_Rivenbrandt);
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

        addToBot(new SFXAction("SvTS:HellfireStrike"));
        addToBot(new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
        if(AbleToNecromancy(Player, NECROMANCY)){
            addToBot(new Necromancy(Player, NECROMANCY, new DamageAllEnemiesAction(Player, this.magicNumber, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL)));
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new HellfireStrike();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_MAGICNUMBER);
            super.upgrade();
        }
    }
}
