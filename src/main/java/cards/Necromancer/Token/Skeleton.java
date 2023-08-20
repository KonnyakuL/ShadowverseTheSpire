package cards.Necromancer.Token;

import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class Skeleton extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:Skeleton");

    public static final String ID = "SvTS:Skeleton";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Token/Skeleton.png";

    private static final int COST = 0;
    private static final int BASE_DMG = 4;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Skeleton(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = BASE_DMG;
        this.exhaust = true;

        this.tags.add(SvTS_Enums.Necromancer);
    }

    @Override
    public AbstractCard makeCopy(){
        return new Skeleton();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            super.upgrade();
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            this.textureImg = "img/Necromancer/cards/Token/Skeleton_Evolved.png";
            loadCardImage(this.textureImg);
        }
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        this.isUsed = true;
        if(this.upgraded){
            addToBot(new SFXAction("SvTS:Skeleton_Evolved"));
        }
        else{
            addToBot(new SFXAction("SvTS:Skeleton"));
        }
        addToBot(new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}
