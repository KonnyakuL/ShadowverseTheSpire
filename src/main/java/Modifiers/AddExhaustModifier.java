package Modifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class AddExhaustModifier extends AbstractCardModifier {
    public static final String MODIFIERID = "SvTS:AddExhaustModifier";

    private AbstractCard card;

    public AddExhaustModifier(AbstractCard card){
        this.card = card;
    }

    @Override
    public String identifier(AbstractCard card){
        return MODIFIERID;
    }

    @Override
    public AbstractCardModifier makeCopy(){
        return new AddExhaustModifier(this.card);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card){
        return rawDescription + CardCrawlGame.languagePack.getUIString(MODIFIERID).TEXT[0];
    }

    @Override
    public void onInitialApplication(AbstractCard card){
        card.exhaust = true;
    }

    @Override
    public boolean shouldApply(AbstractCard card){
        return !card.exhaust;
    }
}
