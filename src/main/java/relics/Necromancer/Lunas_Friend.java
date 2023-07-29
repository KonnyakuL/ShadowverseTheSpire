package relics.Necromancer;

import actions.GainCemetery;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Lunas_Friend extends CustomRelic {
    public static final String ID = "SvTS:Lunas_Friend";
    private static final String IMG_PATH = "img/Necromancer/relics/Lunas_Friend.png";
    private static final String IMG_OTL_PATH = "img/Necromancer/relics/Lunas_Friend.png";
    private static final RelicTier TIER = RelicTier.STARTER;

    public Lunas_Friend(){
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(IMG_OTL_PATH), TIER, LandingSound.CLINK);
    }

    @Override
    public void atTurnStart(){
        this.counter = 1;
    }

    @Override
    public void onUseCard(AbstractCard Card, UseCardAction Action){
        if(this.counter == 1){
            this.counter = 0;
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new GainCemetery(AbstractDungeon.player, AbstractDungeon.player, 1));
        }
    }

    @Override
    public void onVictory(){
        this.counter = -1;
    }

    @Override
    public String getUpdatedDescription(){
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy(){
        return new Lunas_Friend();
    }
}
