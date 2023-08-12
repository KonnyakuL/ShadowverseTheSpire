package Modifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoubleDamageForTurn extends AbstractCardModifier {
    public static final String MODIFIERID = "SvTS:ChangeDamageForTurn";

    private AbstractCard card;

    public DoubleDamageForTurn(AbstractCard card){
        this.card = card;
    }

    @Override
    public boolean removeAtEndOfTurn(AbstractCard card) {
        return true;
    }

    @Override
    public String identifier(AbstractCard card){
        return MODIFIERID;
    }

    @Override
    public AbstractCardModifier makeCopy(){
        return new DoubleDamageForTurn(this.card);
    }

    @Override
    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return damage * 2;
    }
}
