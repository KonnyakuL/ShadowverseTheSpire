package cards.Necromancer.Attack;

import actions.Necromancy;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class DeathlyTyrant extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:DeathlyTyrant");

    public static final String ID = "SvTS:DeathlyTyrant";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Attack/DeathlyTyrant.png";


    private static final int COST = 3;
    private static final int BASE_DMG = 24;
    private static final int UPGRADE_PLUS_DMG = 9;
    private static final int BASE_MAGICNUMBER = 60;
    private static final int UPGRADE_PLUS_MAGICNUMBER = 15;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int NECROMANCY = 20;


    public DeathlyTyrant(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = BASE_DMG;
        this.magicNumber = this.baseMagicNumber = BASE_MAGICNUMBER;

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
            addToBot(new Necromancy(Player, NECROMANCY, new DamageAction(Monster, new DamageInfo(Player, this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY)));
        }
        else {
            addToBot(new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn)));
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new DeathlyTyrant();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_MAGICNUMBER);
            this.textureImg = "img/Necromancer/cards/Attack/DeathlyTyrant_Evolved.png";
            loadCardImage(this.textureImg);
            initializeDescription();
            super.upgrade();
        }
    }

}
