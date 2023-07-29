package powers;

import cards.SvTS_AbstractCard;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Cemetery extends AbstractPower {
    public static final String POWER_ID = "SvTS:Cemetery";
    private static final PowerStrings POWERSTRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = POWERSTRINGS.NAME;
    private static final String[] DESCRIPTIONS = POWERSTRINGS.DESCRIPTIONS;

    public Cemetery(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.amount = Amount;

        String path128 = "img/powers/Cemetery_84.png";
        String path48 = "img/powers/Cemetery_32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action){
        if(!card.hasTag(SvTS_AbstractCard.SvTS_Enums.Banish)){
            this.amount++;
            updateDescription();
        }
    }

}