package cards.Necromancer.Base;

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

public class Strike_Necromancer extends SvTS_AbstractCard {
    //从.json文件中提取键名为 Strike_Necromancer 的信息
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:Strike_Necromancer");

    public static final String ID = "SvTS:Strike_Necromancer";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Base/Strike_Necromancer.png";


    private static final int COST = 1;
    private static final int BASE_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public Strike_Necromancer(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = BASE_DMG;
        this.tags.add(CardTags.STARTER_STRIKE);

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Basic);
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        if(this.upgraded){
            addToBot(new SFXAction("SvTS:Strike_Necromancer_Upgraded"));
        }
        addToBot(new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public AbstractCard makeCopy(){
        return new Strike_Necromancer();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
