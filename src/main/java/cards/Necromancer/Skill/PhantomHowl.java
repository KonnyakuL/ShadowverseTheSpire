package cards.Necromancer.Skill;

import actions.Necromancy;
import basemod.abstracts.CustomCard;
import cards.Necromancer.Attack.Ghost;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class PhantomHowl extends CustomCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:PhantomHowl");

    public static final String ID = "SvTS:PhantomHowl";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    //------------------TODO---------------
    public static final String IMG_PATH = "img/Necromancer/cards/Skill/PhantomHowl.png";


    private static final int COST = 2;
    private static final int NECROMANCY = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public PhantomHowl(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //------------------TODO---------------
    }

    private int GetNecromancy(AbstractPlayer Player){
        if(Player.hasPower("SvTS:Cemetery")){
            for(AbstractPower i : Player.powers){
                if(i.ID.equals("SvTS:Cemetery")){
                    return i.amount;
                }
            }
        }
        return 0;
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        int number = Player.hand.size();
        if(Player.hand.size() > GetNecromancy(Player)){
            number = GetNecromancy(Player);
        }
        while(number-- > 0){
            addToBot(new Necromancy(Player, NECROMANCY, new MakeTempCardInHandAction(new Ghost())));
        }
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBaseCost(1);
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new PhantomHowl();
    }
}