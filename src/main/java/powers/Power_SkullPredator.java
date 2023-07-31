package powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class Power_SkullPredator extends AbstractPower {
    public static final String POWER_ID = "SvTS:Power_SkullPredator";
    private static final PowerStrings POWERSTRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = POWERSTRINGS.NAME;
    private static final String[] DESCRIPTIONS = POWERSTRINGS.DESCRIPTIONS;
    private ArrayList<AbstractCard> CardsToExhaust = new ArrayList<>();

    public Power_SkullPredator(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;

        String path128 = "img/powers/Power_SkullPredator_84.png";
        String path48 = "img/powers/Power_SkullPredator_32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw(){
        addToBot(new HealAction(this.owner, this.owner, 1));
        for(AbstractCard card : AbstractDungeon.player.hand.group){
            if(card.cardID.equals("SvTS:Skeleton")){
                CardsToExhaust.add(card);
            }
        }
        if(!CardsToExhaust.isEmpty()){
            addToBot(new SFXAction("SvTS:Power_SkullPredator_Effect"));
            addToBot(new ExhaustSpecificCardAction(CardsToExhaust.get((int)(Math.random() * CardsToExhaust.size())), AbstractDungeon.player.hand));
            addToBot(new DrawCardAction(1));
        }

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}