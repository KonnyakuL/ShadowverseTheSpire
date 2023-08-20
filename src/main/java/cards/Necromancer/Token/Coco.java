package cards.Necromancer.Token;

import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class Coco extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:Coco");

    public static final String ID = "SvTS:Coco";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Token/Coco.png";

    private static final int COST = 0;
    private static final int BASE_HEAL = 2;
    private static final int UPGRADE_PLUS_HEAL = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;


    public Coco(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = this.heal = this.baseHeal = BASE_HEAL;
        this.exhaust = true;

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Classic);
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.heal));
    }

    @Override
    public AbstractCard makeCopy(){
        return new Coco();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            super.upgrade();
            upgradeName();
            this.heal += UPGRADE_PLUS_HEAL;
            upgradeMagicNumber(UPGRADE_PLUS_HEAL);
        }
    }

}
