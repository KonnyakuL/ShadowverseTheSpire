package powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Power_SpectralSorceress extends AbstractPower {
    public static final String POWER_ID = "SvTS:Power_SpectralSorceress";
    private static final PowerStrings POWERSTRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = POWERSTRINGS.NAME;
    private static final String[] DESCRIPTIONS = POWERSTRINGS.DESCRIPTIONS;

    public Power_SpectralSorceress(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.type = PowerType.BUFF;
        this.amount = -1;
        this.owner = owner;

        String path128 = "img/powers/Power_SpectralSorceress_84.png";
        String path48 = "img/powers/Power_SpectralSorceress_32.png";
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
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }
}