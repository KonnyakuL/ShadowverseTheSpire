package cards.Necromancer.Attack;

import cards.AbstractCustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static org.apache.commons.lang3.math.NumberUtils.max;
import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class LordAtomy extends AbstractCustomCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:LordAtomy");

    public static final String ID = "SvTS:LordAtomy";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    //-----------------------------TODO------------------------
    public static final String IMG_PATH = "img/Necromancer/cards/Attack/LordAtomy.png";

    private static final int COST = 5;
    private static final int BASE_DMG = 54;
    private static final int UPGRADE_PLUS_DMG = 15;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;


    public LordAtomy(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = BASE_DMG;
        this.damage = this.baseDamage;
        //-----------------TODO----------------------
    }

    public void update(){
        if(AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){
            if(AbstractDungeon.player.hand.size() == 9){
                setCostForTurn(max(0, this.cost - 5));
            }
            else{
                setCostForTurn(this.cost);
            }
        }
        super.update();
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        addToBot(new ExhaustAction(AbstractDungeon.player.hand.size() - 1, true, true));
        addToBot(new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn)));
    }

    @Override
    public AbstractCard makeCopy(){
        return new LordAtomy();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

}
