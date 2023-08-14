package cards.Necromancer.Heroes_of_Rivenbrandt;

import actions.Burial;
import actions.Reanimate;
import cards.SvTS_AbstractCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class MisguidedMaiden extends SvTS_AbstractCard {
    private static final CardStrings CARDSTRINGS = CardCrawlGame.languagePack.getCardStrings("SvTS:MisguidedMaiden");

    public static final String ID = "SvTS:MisguidedMaiden";
    public static final String NAME = CARDSTRINGS.NAME;
    public static final String DESCRIPTION = CARDSTRINGS.DESCRIPTION;
    public static final String IMG_PATH = "img/Necromancer/cards/Attack/MisguidedMaiden.png";
    public static final String UPGRADE_PATH = "img/Necromancer/cards/Attack/MisguidedMaiden_Evolved.png";


    private static final int COST = 1;
    private static final int BURIAL_NUMBER = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = SvTS_Necromancer_Color;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public MisguidedMaiden(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.tags.add(SvTS_Enums.Necromancer);
        this.tags.add(SvTS_Enums.Heroes_of_Rivenbrandt);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        int AttackNumber = 0;
        for(AbstractCard card : p.hand.group){
            if(card.type == CardType.ATTACK && card.uuid != this.uuid) AttackNumber++;
        }
        return AttackNumber >= BURIAL_NUMBER && super.canUse(p, m);
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster){
        isUsed = true;

        addToBot(new Burial(1, new DrawCardAction(2)));
        if(this.upgraded) addToBot(new SFXAction("SvTS:MisguidedMaiden_Evolved"));
        else addToBot(new SFXAction("SvTS:MisguidedMaiden"));
    }

    @Override
    public AbstractCard makeCopy(){
        return new MisguidedMaiden();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            this.upgradeName();
            this.rawDescription = CARDSTRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
            this.textureImg = UPGRADE_PATH;
            loadCardImage(this.textureImg);

            if(AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT){
                for(AbstractCard card : AbstractDungeon.player.hand.group) if(card.uuid == this.uuid)
                {
                    addToBot(new SFXAction("SvTS:MisguidedMaiden_Evolving"));
                    addToBot(new Reanimate(AbstractDungeon.player, 2, true));
                }

            }
            super.upgrade();
        }
    }
}
