package cards.Necromancer.Power;

import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.Power_BurialGrounds;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class BurialGrounds extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:BurialGrounds");

    public static final String ID = "SvTS:BurialGrounds";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Power/BurialGrounds.png";


    private static final int COST = 1;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public BurialGrounds(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Classic);
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        addToBot(new ApplyPowerAction(Player, Player, new Power_BurialGrounds(Player, 1)));
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBaseCost(0);
            super.upgrade();
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new BurialGrounds();
    }
}
