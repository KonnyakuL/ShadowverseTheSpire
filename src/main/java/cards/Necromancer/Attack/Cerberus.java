package cards.Necromancer.Attack;

import actions.SvTS_MakeTempCardInHandAction;
import cards.Necromancer.Token.Coco;
import cards.Necromancer.Token.Mimi;
import cards.SvTS_AbstractCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class Cerberus extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:Cerberus");

    public static final String ID = "SvTS:Cerberus";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Attack/Cerberus.png";

    private static final int COST = 2;
    private static final int BASE_DMG = 7;
    private static final int UPGRADE_PLUS_DMG = 4;
    private static final int BASE_BLOCK = 7;
    private static final int UPGRADE_PLUS_BLOCK = 4;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private float RotationTimer;
    private int PreviewIndex;


    public Cerberus(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BASE_BLOCK;
        this.damage = this.baseDamage = BASE_DMG;

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Classic);
    }

    public static ArrayList<AbstractCard> PreviewList(){
        ArrayList<AbstractCard> retVal =new ArrayList<>();
        retVal.add(new Mimi());
        retVal.add(new Coco());
        return retVal;
    }

    public void update(){
        super.update();
        if(this.hb.hovered){
            if(this.RotationTimer <= 0.0F){
                this.RotationTimer = 2.0F;
                this.cardsToPreview = PreviewList().get(this.PreviewIndex).makeCopy();
                if(this.PreviewIndex ==  PreviewList().size() - 1){
                    this.PreviewIndex = 0;
                } else {
                    this.PreviewIndex++;
                }
                if(this.upgraded){
                    this.cardsToPreview.upgrade();
                }
            } else{
                this.RotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;
        addToBot(new GainBlockAction(Player, this.block));
        addToBot(new DamageAction(Monster, new DamageInfo(Player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new SvTS_MakeTempCardInHandAction(new Mimi()));
        addToBot(new SvTS_MakeTempCardInHandAction(new Coco()));
    }

    @Override
    public AbstractCard makeCopy(){
        return new Cerberus();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            super.upgrade();
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.textureImg = "img/Necromancer/cards/Attack/Cerberus_Evolved.png";
            loadCardImage(this.textureImg);
            this.rawDescription = CARDSTRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
