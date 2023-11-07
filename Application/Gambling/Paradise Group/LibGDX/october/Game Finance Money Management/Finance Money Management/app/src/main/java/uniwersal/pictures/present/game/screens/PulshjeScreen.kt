package uniwersal.pictures.present.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import uniwersal.pictures.present.game.LibGDXGame
import uniwersal.pictures.present.game.actors.checkbox.ACheckBox
import uniwersal.pictures.present.game.actors.checkbox.ACheckBoxGroup
import uniwersal.pictures.present.game.actors.scroll.BABLO
import uniwersal.pictures.present.game.actors.scroll.BABLO_NAMKA
import uniwersal.pictures.present.game.utils.Ttime
import uniwersal.pictures.present.game.utils.actor.animHide
import uniwersal.pictures.present.game.utils.actor.animShow
import uniwersal.pictures.present.game.utils.actor.setOnClickListener
import uniwersal.pictures.present.game.utils.advanced.AdvancedScreen
import uniwersal.pictures.present.game.utils.advanced.AdvancedStage
import uniwersal.pictures.present.game.utils.font.CharType
import uniwersal.pictures.present.game.utils.font.FontPath
import uniwersal.pictures.present.game.utils.font.setCharacters
import uniwersal.pictures.present.game.utils.font.setLinear
import uniwersal.pictures.present.game.utils.font.setSize
import uniwersal.pictures.present.game.utils.numStr
import uniwersal.pictures.present.game.utils.region

class PulshjeScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generatorB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Bold))
    private val generatorR = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Regular))
    private val parameter  = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font30    = generatorB.generateFont(parameter.setCharacters(CharType.CYRILLIC).setSize(30))
    private val font42    = generatorR.generateFont(parameter.setCharacters(CharType.ALL).setSize(42))
    private val font24    = generatorR.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"+.%").setSize(24))

    private val image    = Image(game.spriteUtil.teterkaList.random())
    private val nameska  = Label(BABLO_NAMKA.random(), Label.LabelStyle(font30, Color.valueOf("001664"))).apply { wrap = true }
    private val textcos  = Label("1 рубль = 0,${numStr(0,9,3)} ${BABLO.random()}", Label.LabelStyle(font42, Color.valueOf("FF8A00")))
    private val percent  = Label("+${numStr(0,9,1)}.${numStr(0,9,1)}%", Label.LabelStyle(font24, Color.valueOf("149C6B")))
    private val garphon  = Image(game.spriteUtil.wertyuiList.random())

    private val _1h  = ACheckBox(this, ACheckBox.Type._1H)
    private val _1d  = ACheckBox(this, ACheckBox.Type._1D)
    private val _1w  = ACheckBox(this, ACheckBox.Type._1W)
    private val _1m  = ACheckBox(this, ACheckBox.Type._1M)
    private val _1y  = ACheckBox(this, ACheckBox.Type._1Y)
    private val _all = ACheckBox(this, ACheckBox.Type._ALL)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Pulshje.region)
        super.show()
        stageUI.root.animShow(Ttime)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(image, nameska, textcos, percent, garphon)
        image.setBounds(84f, 1445f, 80f, 80f)
        nameska.setBounds(187f, 1445f, 384f, 73f)
        textcos.setBounds(40f, 1348f, 531f, 45f)
        percent.setBounds(11f, 1308f, 97f, 33f)
        garphon.setBounds(40f, 815f, 642f, 377f)

        addActors(_1h, _1d, _1w, _1m, _1y, _all)
        val groped = ACheckBoxGroup()
        _1h.apply {
            setBounds(104f, 701f, 85f, 85f)
            setOnCheckListener { if (it) garphon.drawable = TextureRegionDrawable(game.spriteUtil.wertyuiList.random()) }
            checkBoxGroup = groped
        }
        _1d.apply {
            setBounds(189f, 701f, 85f, 85f)
            setOnCheckListener { if (it) garphon.drawable = TextureRegionDrawable(game.spriteUtil.wertyuiList.random()) }
            checkBoxGroup = groped
            check(false)
        }
        _1w.apply {
            setBounds(274f, 701f, 85f, 85f)
            setOnCheckListener { if (it) garphon.drawable = TextureRegionDrawable(game.spriteUtil.wertyuiList.random()) }
            checkBoxGroup = groped
        }
        _1m.apply {
            setBounds(359f, 701f, 85f, 85f)
            setOnCheckListener { if (it) garphon.drawable = TextureRegionDrawable(game.spriteUtil.wertyuiList.random()) }
            checkBoxGroup = groped
        }
        _1y.apply {
            setBounds(444f, 701f, 85f, 85f)
            setOnCheckListener { if (it) garphon.drawable = TextureRegionDrawable(game.spriteUtil.wertyuiList.random()) }
            checkBoxGroup = groped
        }
        _all.apply {
            setBounds(529f, 701f, 85f, 85f)
            setOnCheckListener { if (it) garphon.drawable = TextureRegionDrawable(game.spriteUtil.wertyuiList.random()) }
            checkBoxGroup = groped
        }

        val back = Actor()
        val b2 = Actor()
        val b3 = Actor()
        addActors(back, b2, b3)
        back.apply {
            setBounds(22f, 1455f, 60f, 60f)
            setOnClickListener { stageUI.root.animHide(Ttime) { game.navigationManager.back() } }
        }
        b2.apply {
            setBounds(26f, 30f, 105f, 98f)
            setOnClickListener { stageUI.root.animHide(Ttime) { game.navigationManager.navigate(
                PrajkeScreen::class.java.name, this@PulshjeScreen::class.java.name
            ) } }
        }
        b3.apply {
            setBounds(314f, 32f, 103f, 98f)
            setOnClickListener { stageUI.root.animHide(Ttime) { game.navigationManager.navigate(
                SevakjeScreen::class.java.name, this@PulshjeScreen::class.java.name
            ) } }
        }
    }

    override fun dispose() {
        super.dispose()
        generatorB.dispose()
        generatorR.dispose()
        font42.dispose()
        font30.dispose()
        font24.dispose()

    }


}