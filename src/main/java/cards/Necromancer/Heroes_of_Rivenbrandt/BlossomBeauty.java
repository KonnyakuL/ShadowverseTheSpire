package cards.Necromancer.Heroes_of_Rivenbrandt;

import actions.Necromancy;
import actions.Waiting;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class BlossomBeauty extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:BlossomBeauty");

    public static final String ID = "SvTS:BlossomBeauty";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Attack/BlossomBeauty.png";
    public static final String UPGRADE_PATH = "img/Necromancer/cards/Attack/BlossomBeauty_Evolved.png";


    private static final int COST = 1;
    private static final int BASE_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int NECROMANCY = 4;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public BlossomBeauty(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = BASE_DMG;

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
        if(AbleToNecromancy(Player, NECROMANCY)){
            addToBot(new Necromancy(Player, NECROMANCY, null));
            upgrade();
            superFlash();
            addToTop(new Waiting(0.5F));
        }
        if(this.upgraded){
            addToTop(new SFXAction("SvTS:BlossomBeauty_Evolved"));
        }
        else{
            addToTop(new SFXAction("SvTS:BlossomBeauty"));
        }
        addToBot(new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
        addToBot(new DrawCardAction(1));
    }

    @Override
    public AbstractCard makeCopy(){
        return new BlossomBeauty();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            super.upgrade();
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            this.damage = this.baseDamage;
            this.isDamageModified = true;
            this.rawDescription = CARDSTRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
            this.textureImg = UPGRADE_PATH;
            loadCardImage(this.textureImg);
        }
    }

    @Override
    public void onMoveToDiscard(){
        degrade();
        superFlash();
    }

    private void degradeName(){
        --this.timesUpgraded;
        this.upgraded = false;
        this.name = NAME;
        initializeTitle();
    }

    private void degradeDamage(){
        this.damage = this.baseDamage = BASE_DMG;
        this.isDamageModified = false;
        this.upgradedDamage = false;
    }

    private void degrade(){
        if(this.upgraded){
            degradeName();
            degradeDamage();
            this.rawDescription = CARDSTRINGS.DESCRIPTION;
            initializeDescription();
            this.textureImg = IMG_PATH;
            loadCardImage(this.textureImg);
        }


    }
}
