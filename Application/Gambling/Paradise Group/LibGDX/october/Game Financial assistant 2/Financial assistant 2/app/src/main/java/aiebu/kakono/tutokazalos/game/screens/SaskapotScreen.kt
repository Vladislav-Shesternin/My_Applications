package aiebu.kakono.tutokazalos.game.screens

import aiebu.kakono.tutokazalos.Scrollkat
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import aiebu.kakono.tutokazalos.game.LibGDXGame
import aiebu.kakono.tutokazalos.game.utils.TIME_ANIM_SCREEN_ALPHA
import aiebu.kakono.tutokazalos.game.utils.TextureEmpty
import aiebu.kakono.tutokazalos.game.utils.actor.animHide
import aiebu.kakono.tutokazalos.game.utils.actor.animShow
import aiebu.kakono.tutokazalos.game.utils.actor.setOnClickListener
import aiebu.kakono.tutokazalos.game.utils.advanced.AdvancedScreen
import aiebu.kakono.tutokazalos.game.utils.advanced.AdvancedStage
import aiebu.kakono.tutokazalos.game.utils.font.CharType
import aiebu.kakono.tutokazalos.game.utils.font.FontPath
import aiebu.kakono.tutokazalos.game.utils.font.setCharacters
import aiebu.kakono.tutokazalos.game.utils.font.setLinear
import aiebu.kakono.tutokazalos.game.utils.font.setSize
import aiebu.kakono.tutokazalos.game.utils.numStr
import aiebu.kakono.tutokazalos.game.utils.region
import aiebu.kakono.tutokazalos.game.utils.runGDX
import java.util.Random
import kotlin.math.cos

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