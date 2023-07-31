package powers;

import Modifiers.AddRetainModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class Power_SkullPredator_Evolved extends AbstractPower {
    public static final String POWER_ID = "SvTS:Power_SkullPredator_Evolved";
    private static final PowerStrings POWERSTRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = POWERSTRINGS.NAME;
    private static final String[] DESCRIPTIONS = POWERSTRINGS.DESCRIPTIONS;
    private ArrayList<AbstractCard> CardsToRetain = new ArrayList<>();

    public Power_SkullPredator_Evolved(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;

        String path128 = "img/powers/Power_SkullPredator_Evolved_84.png";
        String path48 = "img/powers/Power_SkullPredator_Evolved_32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer){
        if(isPlayer){
            addToBot(new GainBlockAction(AbstractDungeon.player, 3));
            for(AbstractCard card : AbstractDungeon.player.hand.group){
                if(card.cardID.equals("SvTS:Skeleton")){
                    CardsToRetain.add(card);
                }
            }
            if(!CardsToRetain.isEmpty()){
                AbstractCard CardToRetain = CardsToRetain.get((int)(Math.random() * CardsToRetain.size()));
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        CardModifierManager.addModifier(CardToRetain, new AddRetainModifier(CardToRetain));
                        addToTop(new SFXAction("SvTS:Power_SkullPredator_Effect"));
                        CardToRetain.superFlash();
                        this.isDone = true;
                    }
                });
            }
        }
    }
}