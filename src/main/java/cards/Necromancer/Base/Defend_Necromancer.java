package cards.Necromancer.Base;

import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class Defend_Necromancer extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:Defend_Necromancer");

    public static final String ID = "SvTS:Defend_Necromancer";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Base/Defend_Necromancer.png";


    private static final int COST = 1;
    private static final int BASE_BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;



    public Defend_Necromancer(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BASE_BLOCK;
        this.tags.add(CardTags.STARTER_DEFEND);

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Basic);
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        if(this.upgraded){
            addToBot(new SFXAction("SvTS:Defend_Necromancer_Upgraded"));
        }
        addToBot(new GainBlockAction(Player, Player, this.block));
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new Defend_Necromancer();
    }
}
