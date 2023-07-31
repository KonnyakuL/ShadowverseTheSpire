package cards.Necromancer.Base;

import actions.GainCemetery;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class ElderSpartoiSoldier extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:ElderSpartoiSoldier");

    public static final String ID = "SvTS:ElderSpartoiSoldier";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Base/ElderSpartoiSoldier.png";


    private static final int COST = 2;
    private static final int BASE_DMG = 11;
    private static final int UPGRADE_PLUS_DMG = 5;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public ElderSpartoiSoldier(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = BASE_DMG;

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Basic);
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;

        if(this.upgraded){
            addToBot(new SFXAction("SvTS:ElderSpartoiSoldier_Evolved"));
        }
        else addToBot(new SFXAction("SvTS:ElderSpartoiSoldier"));
        addToBot(new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
        addToBot(new GainCemetery(AbstractDungeon.player, AbstractDungeon.player, 2));
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
            this.textureImg = "img/Necromancer/cards/Base/ElderSpartoiSoldier_Evolved.png";
            loadCardImage(this.textureImg);
        }
    }
}
