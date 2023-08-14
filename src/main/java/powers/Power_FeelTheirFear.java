package powers;

import actions.GainCemetery;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Power_FeelTheirFear extends AbstractPower {
    public static final String POWER_ID = "SvTS:Power_FeelTheirFear";
    private static final PowerStrings POWERSTRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = POWERSTRINGS.NAME;
    private static final String[] DESCRIPTIONS = POWERSTRINGS.DESCRIPTIONS;

    public Power_FeelTheirFear(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.amount = Amount;

        String path128 = "img/powers/Power_FeelTheirFear_84.png";
        String path48 = "img/powers/Power_FeelTheirFear_32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount * 18, this.amount, this.amount * 18);
    }

    public void atStartOfTurn(){
        addToBot(new GainBlockAction(this.owner, this.owner, this.amount * 18));
        addToBot(new DrawCardAction(this.amount));
        addToBot(new DamageAllEnemiesAction((AbstractPlayer)this.owner, this.amount * 18, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }
}