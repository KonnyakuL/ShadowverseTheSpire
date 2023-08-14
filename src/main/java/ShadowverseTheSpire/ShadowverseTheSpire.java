package ShadowverseTheSpire;

import basemod.BaseMod;
import basemod.interfaces.*;
import cards.Necromancer.Attack.*;
import cards.Necromancer.Base.*;
import cards.Necromancer.Heroes_of_Rivenbrandt.*;
import cards.Necromancer.Omen_of_the_Ten.CharonStygianFerrywoman;
import cards.Necromancer.Omen_of_the_Ten.OrthrusJuniorHellhound;
import cards.Necromancer.Power.*;
import cards.Necromancer.Rebirth_of_Glory.BoneBlast;
import cards.Necromancer.Skill.*;
import cards.Necromancer.Token.Skeleton;
import cards.Neutral.EvolutionPoint;
import characters.Necromancer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import relics.Necromancer.Lunas_Friend;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;
import static pathes.SvTSClassEnum.SvTS_Necromancer_CLASS;

@SpireInitializer
public class ShadowverseTheSpire implements RelicGetSubscriber, PostPowerApplySubscriber, PostExhaustSubscriber, PostBattleSubscriber, PostDungeonInitializeSubscriber, EditCharactersSubscriber, PostInitializeSubscriber, EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber, PostDrawSubscriber, PostEnergyRechargeSubscriber, AddAudioSubscriber {

    // 人物选择界面按钮的图片
    private static final String NECROMANCER_CHOOSE_BUTTON = "img/Necromancer/character/Character_Button.png";
    // 人物选择界面的立绘
    private static final String NECROMANCER_CHOOSE_PORTRAIT = "img/Necromancer/character/Luna.png";
    // 攻击牌的背景（小尺寸）
    private static final String NECROMANCER_BG_ATTACK_512 = "img/Necromancer/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String NECROMANCER_BG_POWER_512 = "img/Necromancer/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String NECROMANCER_BG_SKILL_512 = "img/Necromancer/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String NECROMANCER_SMALL_ORB = "img/Necromancer/512/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String NECROMANCER_BG_ATTACK_1024 = "img/Necromancer/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String NECROMANCER_BG_POWER_1024 = "img/Necromancer/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String NECROMANCER_BG_SKILL_1024 = "img/Necromancer/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String NECROMANCER_BIG_ORB = "img/Necromancer/1024/big_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String NECROMANCER_ENEYGY_ORB = "img/Necromancer/character/card_small_orb.png";
    public static final Color NECROMANCER_COLOR = CardHelper.getColor(200, 20, 200);

    public static ArrayList<AbstractCard> recyclecards = new ArrayList<>();

    public ShadowverseTheSpire(){
        BaseMod.subscribe(this);
        BaseMod.addColor(SvTS_Necromancer_Color, NECROMANCER_COLOR, NECROMANCER_COLOR, NECROMANCER_COLOR, NECROMANCER_COLOR, NECROMANCER_COLOR, NECROMANCER_COLOR, NECROMANCER_COLOR,
                NECROMANCER_BG_ATTACK_512, NECROMANCER_BG_SKILL_512, NECROMANCER_BG_POWER_512, NECROMANCER_SMALL_ORB,
                NECROMANCER_BG_ATTACK_1024, NECROMANCER_BG_SKILL_1024, NECROMANCER_BG_POWER_1024, NECROMANCER_BIG_ORB,
                NECROMANCER_ENEYGY_ORB);
    }

    public static void initialize() {
        new ShadowverseTheSpire();
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new Test());
        //---------------Basic---------------
        BaseMod.addCard(new Strike_Necromancer());
        BaseMod.addCard(new Defend_Necromancer());
        BaseMod.addCard(new ElderSpartoiSoldier());
        BaseMod.addCard(new UndyingResentment());

        BaseMod.addCard(new SkeletonOgre());
        BaseMod.addCard(new BurialGrounds());
        BaseMod.addCard(new GhoulsBanquet());
        BaseMod.addCard(new SkeletonViper());
        BaseMod.addCard(new FoulTempest());
        BaseMod.addCard(new PhantomHowl());
        BaseMod.addCard(new LordAtomy());
        BaseMod.addCard(new Cerberus());
        BaseMod.addCard(new DeathlyTyrant());
        BaseMod.addCard(new ImpiousResurrection());

        //---------------Token---------------
        BaseMod.addCard(new Ghost());
        BaseMod.addCard(new Mimi());
        BaseMod.addCard(new Coco());
        BaseMod.addCard(new Lich());
        BaseMod.addCard(new Skeleton());

        //---------------Rebirth of Glory---------------
        BaseMod.addCard(new BoneBlast());

        //---------------Omen of the Ten---------------
        BaseMod.addCard(new OrthrusJuniorHellhound());
        BaseMod.addCard(new CharonStygianFerrywoman());

        //---------------Heroes of Rivenbrandt---------------
        BaseMod.addCard(new BlossomBeauty());
        BaseMod.addCard(new SpectralSorceress());
        BaseMod.addCard(new BoneMarauder());
        BaseMod.addCard(new SkullPredator());
        BaseMod.addCard(new HellfireStrike());
        BaseMod.addCard(new MisguidedMaiden());
        BaseMod.addCard(new PhantomOfTheStrings());
        BaseMod.addCard(new FeelTheirFear());
        BaseMod.addCard(new EvolutionPoint());
        BaseMod.addCard(new SoultakerScion());
        BaseMod.addCard(new CerberusHowlOfHades());

    }

    @Override
    public void receiveAddAudio(){
        BaseMod.addAudio("SvTS:Test", "sounds/Test.wav");
        BaseMod.addAudio("SvTS:Luna_Chosen", "sounds/Luna_Chosen.mp3");
        //---------------Basic---------------
        BaseMod.addAudio("SvTS:Strike_Necromancer_Upgraded", "sounds/Strike_Necromancer_Upgraded.mp3");
        BaseMod.addAudio("SvTS:Defend_Necromancer_Upgraded", "sounds/Defend_Necromancer_Upgraded.mp3");
        BaseMod.addAudio("SvTS:UndyingResentment", "sounds/UndyingResentment.wav");
        BaseMod.addAudio("SvTS:ElderSpartoiSoldier", "sounds/ElderSpartoiSoldier.wav");
        BaseMod.addAudio("SvTS:ElderSpartoiSoldier_Evolved", "sounds/ElderSpartoiSoldier_Evolved.wav");

        //---------------Token---------------
        BaseMod.addAudio("SvTS:Ghost", "sounds/Ghost.wav");
        BaseMod.addAudio("SvTS:Ghost_Evolved", "sounds/Ghost_Evolved.wav");
        BaseMod.addAudio("SvTS:Skeleton", "sounds/Skeleton.wav");
        BaseMod.addAudio("SvTS:Skeleton_Evolved", "sounds/Skeleton_Evolved.wav");

        //---------------Omen of the Ten---------------
        BaseMod.addAudio("SvTS:OrthrusJuniorHellhound", "sounds/OrthrusJuniorHellhound.wav");
        BaseMod.addAudio("SvTS:OrthrusJuniorHellhound_Evolved", "sounds/OrthrusJuniorHellhound_Evolved.wav");
        BaseMod.addAudio("SvTS:OrthrusJuniorHellhound_CerberusHowlOfHades", "sounds/OrthrusJuniorHellhound_CerberusHowlOfHades.wav");
        BaseMod.addAudio("SvTS:CharonStygianFerrywoman", "sounds/CharonStygianFerrywoman.wav");
        BaseMod.addAudio("SvTS:CharonStygianFerrywoman_Evolved", "sounds/CharonStygianFerrywoman_Evolved.wav");
        BaseMod.addAudio("SvTS:CharonStygianFerrywoman_Effect", "sounds/CharonStygianFerrywoman_Effect.wav");
        BaseMod.addAudio("SvTS:CharonStygianFerrywoman_CerberusHowlOfHades", "sounds/CharonStygianFerrywoman_CerberusHowlOfHades.wav");

        //---------------Heroes of Rivenbrandt---------------
        BaseMod.addAudio("SvTS:BlossomBeauty", "sounds/BlossomBeauty.wav");
        BaseMod.addAudio("SvTS:BlossomBeauty_Evolved", "sounds/BlossomBeauty_Evolved.wav");
        BaseMod.addAudio("SvTS:SpectralSorceress", "sounds/SpectralSorceress.wav");
        BaseMod.addAudio("SvTS:SpectralSorceress_Evolved", "sounds/SpectralSorceress_Evolved.wav");
        BaseMod.addAudio("SvTS:Power_SpectralSorceress_Effect", "sounds/Power_SpectralSorceress_Effect.wav");
        BaseMod.addAudio("SvTS:BoneMarauder", "sounds/BoneMarauder.wav");
        BaseMod.addAudio("SvTS:BoneMarauder_Evolved", "sounds/BoneMarauder_Evolved.wav");
        BaseMod.addAudio("SvTS:SkullPredator", "sounds/SkullPredator.wav");
        BaseMod.addAudio("SvTS:SkullPredator_Evolved", "sounds/SkullPredator_Evolved.wav");
        BaseMod.addAudio("SvTS:SkullPredator_Enhance", "sounds/SkullPredator_Enhance.wav");
        BaseMod.addAudio("SvTS:Power_SkullPredator_Effect", "sounds/Power_SkullPredator_Effect.wav");
        BaseMod.addAudio("SvTS:HellfireStrike", "sounds/HellfireStrike.wav");
        BaseMod.addAudio("SvTS:MisguidedMaiden", "sounds/MisguidedMaiden.wav");
        BaseMod.addAudio("SvTS:MisguidedMaiden_Evolved", "sounds/MisguidedMaiden_Evolved.wav");
        BaseMod.addAudio("SvTS:MisguidedMaiden_Evolving", "sounds/MisguidedMaiden_Evolving.wav");
        BaseMod.addAudio("SvTS:PhantomOfTheStrings", "sounds/PhantomOfTheStrings.wav");
        BaseMod.addAudio("SvTS:PhantomOfTheStrings_Evolved", "sounds/PhantomOfTheStrings_Evolved.wav");
        BaseMod.addAudio("SvTS:FeelTheirFear", "sounds/FeelTheirFear.wav");
        BaseMod.addAudio("SvTS:SoultakerScion", "sounds/SoultakerScion.wav");
        BaseMod.addAudio("SvTS:SoultakerScion_Evolved", "sounds/SoultakerScion_Evolved.wav");
        BaseMod.addAudio("SvTS:Power_SoultakerScion_Effect", "sounds/Power_SoultakerScion_Effect.wav");
        BaseMod.addAudio("SvTS:CerberusHowlOfHades", "sounds/CerberusHowlOfHades.wav");
        BaseMod.addAudio("SvTS:CerberusHowlOfHades_Evolved", "sounds/CerberusHowlOfHades_Evolved.wav");
        BaseMod.addAudio("SvTS:Power_CerberusHowlOfHades_Effect", "sounds/Power_CerberusHowlOfHades_Effect.wav");
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Necromancer(CardCrawlGame.playerName), NECROMANCER_CHOOSE_BUTTON, NECROMANCER_CHOOSE_PORTRAIT, SvTS_Necromancer_CLASS);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new Lunas_Friend(), SvTS_Necromancer_Color);
    }

    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/ShadowverseTheSpire_cards_" + lang + ".json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "localization/ShadowverseTheSpire_characters_" + lang + ".json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/ShadowverseTheSpire_relics_" + lang + ".json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/ShadowverseTheSpire_powers_" + lang + ".json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "localization/ShadowverseTheSpire_UI_" + lang + ".json");
    }

    @Override
    public void receivePostExhaust(AbstractCard card){}

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower pow, AbstractCreature target, AbstractCreature owner) {}

    @Override
    public void receivePowersModified() {}

    @Override
    public void receivePostDungeonInitialize() {}

    @Override
    public void receivePostDraw(AbstractCard arg0) {}

    @Override
    public void receiveEditKeywords() {
        String lang = "ZHS";
        if(Settings.language == Settings.GameLanguage.ZHS){
            lang = "ZHS";
        }
        String keywordsPath = "localization/ShadowverseTheSpire_keywords_" + lang + ".json";
        String loadJson =Gdx.files.internal(keywordsPath).readString(String.valueOf(StandardCharsets.UTF_8));
        Gson gson = new Gson();
        Keywords keywords = (Keywords)gson.fromJson(loadJson, Keywords.class);
        Keyword[] var4 = keywords.keywords;
        int var5 = var4.length;
        for (int var6 = 0; var6 < var5; var6++) {
            Keyword key = var4[var6];
            BaseMod.addKeyword(key.NAMES, key.DESCRIPTION);
        }
   }

    @Override
    public void receiveRelicGet(AbstractRelic relic) {}

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {}

    @Override
    public void receivePostBattle(AbstractRoom r) {}

    @Override
    public void receivePostInitialize() {}

    @Override
    public void receivePostEnergyRecharge() {
        Iterator<AbstractCard> var1 = recyclecards.iterator();

        while (var1.hasNext()) {
            AbstractCard c = var1.next();
            AbstractCard card = c.makeStatEquivalentCopy();
            AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, false, true, true));
        }
        recyclecards.clear();
    }

    class Keywords {
        Keyword[] keywords;
    }
}
