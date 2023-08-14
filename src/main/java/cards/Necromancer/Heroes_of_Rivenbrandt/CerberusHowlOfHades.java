package cards.Necromancer.Heroes_of_Rivenbrandt;

import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class CerberusHowlOfHades extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:CerberusHowlOfHades");

    public static final String ID = "SvTS:CerberusHowlOfHades";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Power/CerberusHowlOfHades.png";
    public static final String UPGRADE_PATH = "img/Necromancer/cards/Power/CerberusHowlOfHades_Evolved.png";


    private static final int COST = 3;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;



    public CerberusHowlOfHades() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Heroes_of_Rivenbrandt);
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;

        if(this.upgraded) addToBot(new SFXAction("SvTS:CerberusHowlOfHades_Evolved"));
        else addToBot(new SFXAction("SvTS:CerberusHowlOfHades"));
    }

    @Override
    public AbstractCard makeCopy(){
        return new CerberusHowlOfHades();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            this.textureImg = UPGRADE_PATH;
            loadCardImage(this.textureImg);
            super.upgrade();
        }
    }
}
