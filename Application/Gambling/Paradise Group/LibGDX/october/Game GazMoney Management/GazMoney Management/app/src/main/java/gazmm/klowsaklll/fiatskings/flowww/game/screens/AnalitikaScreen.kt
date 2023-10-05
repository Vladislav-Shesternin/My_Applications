package gazmm.klowsaklll.fiatskings.flowww.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import gazmm.klowsaklll.fiatskings.flowww.game.LibGDXGame
import gazmm.klowsaklll.fiatskings.flowww.game.actors.checkbox.ACheckBox
import gazmm.klowsaklll.fiatskings.flowww.game.actors.checkbox.ACheckBoxGroup
import gazmm.klowsaklll.fiatskings.flowww.game.utils.TIME_ANIM_SCREEN_ALPHA
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.animHide
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.animShow
import gazmm.klowsaklll.fiatskings.flowww.game.utils.actor.setOnClickListener
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedScreen
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedStage
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.CharType
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.FontPath
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.setCharacters
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.setLinear
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.setSize
import gazmm.klowsaklll.fiatskings.flowww.game.utils.numStr

var codeNAME = "RUB"

class AnalitikaScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generator  = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Bold))
    private val generatorR = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Regular))
    private val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font29    = generator.generateFont(parameter.setCharacters(CharType.LATIN).setSize(29))
    private val font40    = generator.generateFont(parameter.setCharacters(CharType.LATIN.chars+CharType.NUMBERS.chars+"=,").setSize(40))
    private val font23    = generatorR.generateFont(parameter.setCharacters(CharType.LATIN.chars+CharType.NUMBERS.chars+"+.%").setSize(23))

    private val image    = Image(game.spriteUtil._price_anal)
    private val iconkal = Image(game.spriteUtil.lisovka.random())
    private val nameska  = Label(curentNamka?.symbols?.get(codeNAME) ?: "Russian ruble", Label.LabelStyle(font29, Color.valueOf("000D26"))).apply { wrap = true }
    private val textcos  = Label("1 ruble = ${curent?.rates?.get(codeNAME)?.toRub ?: 1.0} $codeNAME", Label.LabelStyle(font40, Color.valueOf("011D72")))
    private val percent  = Label("+${numStr(0,9,1)}.${numStr(0,9,1)}%      Today", Label.LabelStyle(font23, Color.valueOf("049C6B")))
    private val garphon  = Image(game.spriteUtil.tlist.random())

    private val _1h  = ACheckBox(this, ACheckBox.Type._1H)
    private val _1d  = ACheckBox(this, ACheckBox.Type._1D)
    private val _1w  = ACheckBox(this, ACheckBox.Type._1W)
    private val _1m  = ACheckBox(this, ACheckBox.Type._1M)
    private val _1y  = ACheckBox(this, ACheckBox.Type._1Y)
    private val _all = ACheckBox(this, ACheckBox.Type._ALL)

    override fun show() {
        stageUI.root.animHide()
//        setBackground(game.spriteUtil.HOMEK.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(image, iconkal, nameska, textcos, percent, garphon)
        image.setBounds(21f, 660f, 641f, 794f)
        iconkal.setBounds(90f, 1390f, 71f, 71f)
        nameska.setBounds(179f, 1394f, 338f, 62f)
        textcos.setBounds(39f, 1293f, 587f, 44f)
        percent.setBounds(38f, 1256f, 170f, 31f)
        garphon.setBounds(38f, 782f, 616f, 362f)

        addActors(_1h, _1d, _1w, _1m, _1y, _all)
        val groped = ACheckBoxGroup()
        _1h.apply {
            setBounds(100f, 673f, 81f, 81f)
            setOnCheckListener { if (it) garphon.drawable = TextureRegionDrawable(game.spriteUtil.tlist.random()) }
            checkBoxGroup = groped
        }
        _1d.apply {
            setBounds(181f, 673f, 81f, 81f)
            setOnCheckListener { if (it) garphon.drawable = TextureRegionDrawable(game.spriteUtil.tlist.random()) }
            checkBoxGroup = groped
            check(false)
        }
        _1w.apply {
            setBounds(263f, 673f, 81f, 81f)
            setOnCheckListener { if (it) garphon.drawable = TextureRegionDrawable(game.spriteUtil.tlist.random()) }
            checkBoxGroup = groped
        }
        _1m.apply {
            setBounds(345f, 673f, 81f, 81f)
            setOnCheckListener { if (it) garphon.drawable = TextureRegionDrawable(game.spriteUtil.tlist.random()) }
            checkBoxGroup = groped
        }
        _1y.apply {
            setBounds(426f, 673f, 81f, 81f)
            setOnCheckListener { if (it) garphon.drawable = TextureRegionDrawable(game.spriteUtil.tlist.random()) }
            checkBoxGroup = groped
        }
        _all.apply {
            setBounds(508f, 673f, 81f, 81f)
            setOnCheckListener { if (it) garphon.drawable = TextureRegionDrawable(game.spriteUtil.tlist.random()) }
            checkBoxGroup = groped
        }

        val b1 = Image(game.spriteUtil._hpre)
        val b2 = Image(game.spriteUtil._cpre)
        val b3 = Image(game.spriteUtil._adef)
        addActors(b1, b2, b3)
        b1.apply {
            setBounds(40f, 29f, 74f, 94f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(HomekScreen::class.java.name, this::class.java.name) } }
        }
        b2.apply {
            setBounds(278f, 29f, 112f, 94f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(CatalogScreen::class.java.name, this::class.java.name) } }
        }
        b3.apply {
            setBounds(553f, 29f, 100f, 94f)
//            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(AnalitikaScreen::class.java.name) } }
        }
    }

    override fun dispose() {
        super.dispose()
        generator.dispose()
        font29.dispose()
        font40.dispose()
    }

}