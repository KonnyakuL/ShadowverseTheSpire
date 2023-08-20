package cards.Necromancer.Heroes_of_Rivenbrandt;

import Modifiers.AddExhaustModifier;
import actions.SvTS_MakeTempCardInHandAction;
import basemod.helpers.CardModifierManager;
import cards.Necromancer.Omen_of_the_Ten.CharonStygianFerrywoman;
import cards.Necromancer.Omen_of_the_Ten.OrthrusJuniorHellhound;
import cards.SvTS_AbstractCard;
import characters.SvTS_AbstractPlayer;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.Power_CerberusHowlOfHades;

import java.util.ArrayList;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class CerberusHowlOfHades extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:CerberusHowlOfHades");

    public static final String ID = "SvTS:CerberusHowlOfHades";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Power/CerberusHowlOfHades.png";
    public static final String UPGRADE_PATH = "img/Necromancer/cards/Power/CerberusHowlOfHades_Evolved.png";


    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private float RotationTimer;
    private int PreviewIndex;


    public CerberusHowlOfHades() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.selfRetain = true;

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Heroes_of_Rivenbrandt);
    }

    public static ArrayList<AbstractCard> PreviewList(){
        ArrayList<AbstractCard> retVal =new ArrayList<>();
        retVal.add(new OrthrusJuniorHellhound());
        retVal.add(new CharonStygianFerrywoman());
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
            } else{
                this.RotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;

        if(this.upgraded) addToBot(new SFXAction("SvTS:CerberusHowlOfHades_Evolved"));
        else addToBot(new SFXAction("SvTS:CerberusHowlOfHades"));
        AbstractCard tmp;
        if(Player instanceof SvTS_AbstractPlayer){
            if(((SvTS_AbstractPlayer) Player).EvolvedLastTurn) tmp = new CharonStygianFerrywoman();
            else tmp = new OrthrusJuniorHellhound();
        }
        else tmp = new OrthrusJuniorHellhound();
        tmp.freeToPlayOnce = true;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                CardModifierManager.addModifier(tmp, new AddExhaustModifier(tmp));
                this.isDone = true;
            }
        });
        addToBot(new ApplyPowerAction(Player, Player, new Power_CerberusHowlOfHades(Player, 1)));
        addToBot(new SvTS_MakeTempCardInHandAction(tmp));
    }

    @Override
    public AbstractCard makeCopy(){
        return new CerberusHowlOfHades();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            super.upgrade();
            upgradeName();
            this.textureImg = UPGRADE_PATH;
            loadCardImage(this.textureImg);
            upgradeBaseCost(UPGRADE_COST);
        }
    }
}
