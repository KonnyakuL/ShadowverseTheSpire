package cards;

import actions.GainCemetery;
import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class SvTS_AbstractCard extends CustomCard {
    public int BaseCost;
    public static class SvTS_Enums{
        @SpireEnum public static AbstractCard.CardTags Necromancer;

        @SpireEnum public static AbstractCard.CardTags Basic;
        @SpireEnum public static AbstractCard.CardTags Classic;
        @SpireEnum public static AbstractCard.CardTags Darkness_Evolved;
        @SpireEnum public static AbstractCard.CardTags Rise_of_Bahamut;
        @SpireEnum public static AbstractCard.CardTags Tempest_of_the_Gods;
        @SpireEnum public static AbstractCard.CardTags Wonderland_Dreams;
        @SpireEnum public static AbstractCard.CardTags Starforged_Legends;
        @SpireEnum public static AbstractCard.CardTags Chronogenesis;
        @SpireEnum public static AbstractCard.CardTags Dawnbreak_Nightedge;
        @SpireEnum public static AbstractCard.CardTags Brigade_of_the_Sky;
        @SpireEnum public static AbstractCard.CardTags Omen_of_the_Ten;
        @SpireEnum public static AbstractCard.CardTags Altersphere;
        @SpireEnum public static AbstractCard.CardTags Steel_Rebellion;
        @SpireEnum public static AbstractCard.CardTags Rebirth_of_Glory;
        @SpireEnum public static AbstractCard.CardTags Verdant_Conflict;
        @SpireEnum public static AbstractCard.CardTags Ultimate_Colosseum;
        @SpireEnum public static AbstractCard.CardTags World_Uprooted;
        @SpireEnum public static AbstractCard.CardTags Fortunes_Hand;
        @SpireEnum public static AbstractCard.CardTags Storm_Over_Rivayle;
        @SpireEnum public static AbstractCard.CardTags Eternal_Awakening;
        @SpireEnum public static AbstractCard.CardTags Darkness_Over_Vellsar;
        @SpireEnum public static AbstractCard.CardTags Renascent_Chronicles;
        @SpireEnum public static AbstractCard.CardTags Dawn_of_Calamity;
        @SpireEnum public static AbstractCard.CardTags Omen_of_Storms;
        @SpireEnum public static AbstractCard.CardTags Edge_of_Paradise;
        @SpireEnum public static AbstractCard.CardTags Roar_of_the_Godwyrm;
        @SpireEnum public static AbstractCard.CardTags Celestial_Dragonblade;
        @SpireEnum public static AbstractCard.CardTags Eightfold_Abyss_Azvaldt;
        @SpireEnum public static AbstractCard.CardTags Academy_of_Ages;
        @SpireEnum public static AbstractCard.CardTags Heroes_of_Rivenbrandt;

        @SpireEnum public static AbstractCard.CardTags Academic;

        @SpireEnum public static AbstractCard.CardTags Banish;
    }

    public SvTS_AbstractCard(String ID, String NAME, String IMG_PATH, int COST, String DESCRIPTION, CardType TYPE, CardColor COLOR, CardRarity RARITY, CardTarget TARGET){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.BaseCost = COST;
        isUsed = false;
    }



    @Override
    public void onMoveToDiscard(){
        isUsed = false;
    }

    @Override
    public void triggerOnExhaust(){
        if(!isUsed && !this.cardID.equals("SvTS:Ghost")){
            addToBot(new GainCemetery(AbstractDungeon.player, AbstractDungeon.player, 1));
        }
    }

    @Override
    public void upgradeBaseCost(int newBaseCost){
        this.BaseCost = newBaseCost;
        super.upgradeBaseCost(newBaseCost);
    }
}
