package cards.Necromancer.Omen_of_the_Ten;

import actions.Necromancy;
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
import com.megacrit.cardcrawl.powers.AbstractPower;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class OrthrusJuniorHellhound extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:OrthrusJuniorHellhound");

    public static final String ID = "SvTS:OrthrusJuniorHellhound";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Omen_of_the_Ten/OrthrusJuniorHellhound.png";
    public static final String UPGRADE_PATH = "img/Necromancer/cards/Omen_of_the_Ten/OrthrusJuniorHellhound_Evolved.png";


    private static final int COST = 2;
    private static final int BASE_DMG = 9;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int NECROMANCY = 3;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public OrthrusJuniorHellhound() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = BASE_DMG;

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Omen_of_the_Ten);
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

        if(Player.hasPower("SvTS:Power_CerberusHowlOfHades")) addToBot(new SFXAction("SvTS:OrthrusJuniorHellhound_CerberusHowlOfHades"));
        else if(this.upgraded) addToBot(new SFXAction("SvTS:OrthrusJuniorHellhound_Evolved"));
            else addToBot(new SFXAction("SvTS:OrthrusJuniorHellhound"));
        addToBot(new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if(AbleToNecromancy(Player, NECROMANCY)){
            addToBot(new Necromancy(Player, NECROMANCY, new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL)));
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new OrthrusJuniorHellhound();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            super.upgrade();
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            this.textureImg = UPGRADE_PATH;
            loadCardImage(this.textureImg);
        }
    }
}
