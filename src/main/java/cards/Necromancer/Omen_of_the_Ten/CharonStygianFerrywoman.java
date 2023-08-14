package cards.Necromancer.Omen_of_the_Ten;

import actions.Action_CharonStygianFerrywoman;
import actions.ChangeCostForTurn;
import actions.Necromancy;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class CharonStygianFerrywoman extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:CharonStygianFerrywoman");

    public static final String ID = "SvTS:CharonStygianFerrywoman";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Omen_of_the_Ten/CharonStygianFerrywoman.png";
    public static final String UPGRADE_PATH = "img/Necromancer/cards/Omen_of_the_Ten/CharonStygianFerrywoman_Evolved.png";


    private static final int COST = 2;
    private static final int BASE_BLOCK = 10;
    private static final int UPGRADE_PLUS_BLOCK = 6;
    private static final int NECROMANCY = 5;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;


    public CharonStygianFerrywoman() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BASE_BLOCK;

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

        if(Player.hasPower("SvTS:CerberusHowlOfHades")) addToBot(new SFXAction("SvTS:CharonStygianFerrywoman_CerberusHowlOfHades"));
        else if(this.upgraded) addToBot(new SFXAction("SvTS:CharonStygianFerrywoman_Evolved"));
        else addToBot(new SFXAction("SvTS:CharonStygianFerrywoman"));
        addToBot(new GainBlockAction(Player, this.block));
        addToBot(new Action_CharonStygianFerrywoman(Player));
        if(AbleToNecromancy(Player, NECROMANCY)){
            addToBot(new Necromancy(Player, NECROMANCY, null));
            addToBot(new ChangeCostForTurn(1, 3));
            addToBot(new SFXAction("SvTS:CharonStygianFerrywoman_Effect"));
        }

    }

    @Override
    public AbstractCard makeCopy(){
        return new CharonStygianFerrywoman();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.textureImg = UPGRADE_PATH;
            loadCardImage(this.textureImg);
            super.upgrade();
        }
    }
}
