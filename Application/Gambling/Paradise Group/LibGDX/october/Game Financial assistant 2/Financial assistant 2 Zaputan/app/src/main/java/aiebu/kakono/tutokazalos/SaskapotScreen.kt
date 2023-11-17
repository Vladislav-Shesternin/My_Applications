package aiebu.kakono.tutokazalos

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import aiebu.kakono.tutokazalos.soloha.pisoha.TIME_ANIM_SCREEN_ALPHA
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.animHide
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.setOnClickListener
import aiebu.kakono.tutokazalos.soloha.pisoha.front.AdvancedScreen
import aiebu.kakono.tutokazalos.soloha.pisoha.front.AdvancedStage
import aiebu.kakono.tutokazalos.soloha.pisoha.front.CharType
import aiebu.kakono.tutokazalos.soloha.pisoha.front.FontPath
import aiebu.kakono.tutokazalos.soloha.pisoha.front.setCharacters
import aiebu.kakono.tutokazalos.soloha.pisoha.front.setLinear
import aiebu.kakono.tutokazalos.soloha.pisoha.front.setSize
import aiebu.kakono.tutokazalos.soloha.pisoha.numStr
import aiebu.kakono.tutokazalos.soloha.pisoha.region

class SaskapotScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.RG))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font61 = generatorSB.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+".").setSize(61))
    private val font31 = generatorSB.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+".+").setSize(31))
    private val font23 = generatorRG.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"+%").setSize(23))

    private val babkiLbl = Label("${numStr(1,9,1)}.${numStr(100,888,1)}.${numStr(10,88,1)}", Label.LabelStyle(font61, Color.WHITE))
    private val costeLbl = Label("+${numStr(1,9,1)}.${numStr(100,888,1)}.${numStr(10,88,1)}", Label.LabelStyle(font31, Color.WHITE)).apply { setAlignment(Align.right) }
    private val persiLbl = Label("+${numStr(1,99,1)}%", Label.LabelStyle(font23, Color.valueOf("03FF6A"))).apply { setAlignment(Align.right) }

    private val skoroBude = Scrollkat(this)

    override fun show() {
//        stageUI.root.animHide()
        setBackground(game.spriteUtil.SOSKAPOT.region)
        super.show()
//        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(babkiLbl, costeLbl, persiLbl, skoroBude)
        babkiLbl.setBounds(69f, 1228f, 374f, 48f)
        costeLbl.setBounds(499f, 1254f, 149f, 22f)
        persiLbl.setBounds(525f, 1227f, 123f, 17f)
        skoroBude.setBounds(39f, 0f, 646f, 966f)

        val sss = Actor()
        addActor(sss)
        sss.apply {
            setBounds(39f, 1110f, 638f, 247f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.navigationManager.navigate(IbizjScreen::class.java.name, this@SaskapotScreen::class.java.name)
            } }
        }
    }

    override fun dispose() {
        super.dispose()
        generatorSB.dispose()
        generatorRG.dispose()
        font61.dispose()
        font31.dispose()
        font23.dispose()
    }

}