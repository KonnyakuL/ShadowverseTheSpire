package characters;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import pathes.SvTSClassEnum;
import powers.Cemetery;

import java.util.ArrayList;

import static pathes.AbstractCardEnum.SvTS_Necromancer_Color;

public class Necromancer extends CustomPlayer {
    private static final int ENERGY_PER_TURN = 3;
    private static final int STARTING_HP = 75;
    private static final int MAX_HP = 75;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 0;
    private static final int CARD_DRAW = 5;
    private static final int ASCENSION_MAX_HP_LOSS = 5;
    //------------TODO-----------
    private static final String STAND_PATH = "img/Necromancer/character/Stand.png";
    private static final String SHOULDER_1_PATH = "img/Necromancer/character/Shoulder1.png";
    private static final String SHOULDER_2_PATH = "img/Necromancer/character/Shoulder2.png";
    private static final String CORPSE_PATH = "img/Necromancer/character/Corpse.png";
    private static final String ORB_VFX = "img/Necromancer/UI/orb/Layer_Blank.png";

    public static final Color NECROMANCER_COLOR = CardHelper.getColor(200, 20, 200);
    // 战斗界面左下角能量图标的每个图层
    private static final String[] ORB_TEXTURES = new String[]{
            "img/Necromancer/UI/orb/Layer_Blank.png",
            "img/Necromancer/UI/orb/Layer_Blank.png",
            "img/Necromancer/UI/orb/Layer_Blank.png",
            "img/Necromancer/UI/orb/Layer_Blank.png",
            "img/Necromancer/UI/orb/Layer_Blank.png",
            "img/Necromancer/UI/orb/Layer.png",
            "img/Necromancer/UI/orb/Layer_Blank.png",
            "img/Necromancer/UI/orb/Layer_Blank.png",
            "img/Necromancer/UI/orb/Layer_Blank.png",
            "img/Necromancer/UI/orb/Layer_Blank.png",
            "img/Necromancer/UI/orb/Layer_Blank.png"

    };
    // 每个图层的旋转速度
    private static final float[] LAYER_SPEED = null;//new float[]{0.0F};
    // 人物的本地化文本，如卡牌的本地化文本一样，如何书写见下
    private static final CharacterStrings CHARACTERSTRINGS = CardCrawlGame.languagePack.getCharacterString("SvTS:Necromancer");


    public Necromancer(String Name){
        super(Name, SvTSClassEnum.SvTS_Necromancer_CLASS, new CustomEnergyOrb(ORB_TEXTURES, ORB_VFX, null), null, null);
        // 人物对话气泡的大小，如果游戏中尺寸不对在这里修改（libgdx的坐标轴左下为原点）
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);
        // 初始化你的人物，如果你的人物只有一张图，那么第一个参数填写你人物图片的路径。
        this.initializeClass(STAND_PATH,
                SHOULDER_2_PATH, SHOULDER_1_PATH,
                CORPSE_PATH, // 人物死亡图像
                this.getLoadout(),
                0.0F, 0.0F,
                250.0F, 294.0F, // 人物碰撞箱大小，越大的人物模型这个越大
                new EnergyManager(ENERGY_PER_TURN) // 初始每回合的能量
        );
    }

    public void useCard(AbstractCard c, AbstractMonster monster, int energyOnUse){
        super.useCard(c, monster, energyOnUse);
        if(!hasPower("SvTS:Cemetery")){
            this.powers.add(new Cemetery(this, 0));
        }
    }

    @Override
    public ArrayList<String> getStartingDeck(){
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("SvTS:Strike_Necromancer");
        retVal.add("SvTS:Strike_Necromancer");
        retVal.add("SvTS:Strike_Necromancer");
        retVal.add("SvTS:Strike_Necromancer");
        retVal.add("SvTS:Strike_Necromancer");
        retVal.add("SvTS:Defend_Necromancer");
        retVal.add("SvTS:Defend_Necromancer");
        retVal.add("SvTS:Defend_Necromancer");
        retVal.add("SvTS:Defend_Necromancer");
        retVal.add("SvTS:Defend_Necromancer");
        retVal.add("SvTS:ElderSpartoiSoldier");
        retVal.add("SvTS:UndyingResentment");
        System.out.println("初始牌组加载完毕");
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics(){
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("SvTS:Lunas_Friend");
        UnlockTracker.markRelicAsSeen("SvTS:Lunas_Friend");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout(){
        String title;
        String flavor;
        if(Settings.language == Settings.GameLanguage.ZHS){
            title = CHARACTERSTRINGS.NAMES[0];
            flavor = CHARACTERSTRINGS.TEXT[0];
        } else{
            //----------TODO-------------
            //--------English Name----------
            title = CHARACTERSTRINGS.NAMES[0];
            flavor = CHARACTERSTRINGS.TEXT[0];
        }
        return new CharSelectInfo(title, flavor, STARTING_HP, MAX_HP, HAND_SIZE, STARTING_GOLD, CARD_DRAW, this, this.getStartingRelics(), this.getStartingDeck(), false);
    }

    // 人物名字（出现在游戏左上角）
    @Override
    public String getTitle(PlayerClass playerClass) {
        if(Settings.language == Settings.GameLanguage.ZHS){
            return CHARACTERSTRINGS.NAMES[0];
        } else{
            //-----------TODO-----------
            //---------English Name--------
            return CHARACTERSTRINGS.NAMES[0];
        }
    }

    // 你的卡牌颜色
    @Override
    public AbstractCard.CardColor getCardColor() {
        return SvTS_Necromancer_Color;
    }

    // 翻牌事件出现的你的职业牌（一般设为打击）
    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    // 卡牌轨迹颜色
    @Override
    public Color getCardTrailColor() {
        return NECROMANCER_COLOR;
    }

    // 高进阶带来的生命值损失
    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    // 卡牌的能量字体，没必要修改
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    // 人物选择界面点击你的人物按钮时触发的方法，这里为屏幕轻微震动
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

//    public void updateOrb(int OrbCount){
//        this.energyOrb.updateOrb(OrbCount);
//    }

    // 自定义模式选择你的人物时播放的音效
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    // 游戏中左上角显示在你的名字之后的人物名称
    @Override
    public String getLocalizedCharacterName() {
        if(Settings.language == Settings.GameLanguage.ZHS){
            return CHARACTERSTRINGS.NAMES[0];
        } else{
            //-----------TODO-----------
            //---------English Name--------
            return CHARACTERSTRINGS.NAMES[0];
        }
    }
    // 碎心图片
    @Override
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        // 有两个参数的，第二个参数表示出现图片时播放的音效
        panels.add(new CutscenePanel("img/Necromancer/character/Victory1.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("img/Necromancer/character/Victory2.png"));
        panels.add(new CutscenePanel("img/Necromancer/character/Victory3.png"));
        return panels;
    }

    // 创建人物实例，照抄
    @Override
    public AbstractPlayer newInstance() {
        return new Necromancer(this.name);
    }

    // 第三章面对心脏说的话（例如战士是“你握紧了你的长刀……”之类的）
    @Override
    public String getSpireHeartText() {
        return CHARACTERSTRINGS.TEXT[1];
    }

    // 打心脏的颜色，不是很明显
    @Override
    public Color getSlashAttackColor() {
        return NECROMANCER_COLOR;
    }

    // 吸血鬼事件文本，主要是他（索引为0）和她（索引为1）的区别（机器人另外）
    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

    // 卡牌选择界面选择该牌的颜色
    @Override
    public Color getCardRenderColor() {
        return NECROMANCER_COLOR;
    }

    // 第三章面对心脏造成伤害时的特效
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }
}
