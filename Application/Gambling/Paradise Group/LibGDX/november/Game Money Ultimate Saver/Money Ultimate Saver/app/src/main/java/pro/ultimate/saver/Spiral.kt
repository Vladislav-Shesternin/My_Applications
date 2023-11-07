package pro.ultimate.saver

import pro.ultimate.saver.game.Bubeka
import pro.ultimate.saver.game.VerticalGroup
import pro.ultimate.saver.game.utils.advanced.AdvancedGroup
import pro.ultimate.saver.game.utils.advanced.AdvancedScreen
import pro.ultimate.saver.game.utils.font.CharType
import pro.ultimate.saver.game.utils.font.FontPath
import pro.ultimate.saver.game.utils.font.setCharacters
import pro.ultimate.saver.game.utils.font.setLinear
import pro.ultimate.saver.game.utils.font.setSize
import pro.ultimate.saver.game.utils.numStr
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane

class Spiral(override val screen: AdvancedScreen): AdvancedGroup() {

    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font14 = generatorRG.generateFont(parameter.setCharacters(CharType.ALL).setSize(14))

    private val vert = VerticalGroup(screen, startGap = 0f, gap = 10f, alignment = VerticalGroup.Alignment.TOP, direction = VerticalGroup.Direction.DOWN)
    private val srce = ScrollPane(vert)

    private val listN = listOf(
        "Февраля",
        "Декабря",
        "Сентября",
        "Октября",
        "Августа",
        "Января",
        "Ноября",
        "Июля",
    )

    override fun addActorsOnGroup() {
        addAndFillActor(srce)

        repeat((2..5).random()) {
            addLbl()
            repeat((2..3).random()) { vert.addActor(Bubeka(screen).apply { setSize(321f, 45f) }) }
        }
    }

    private fun addLbl() {
        vert.addActor(Label("${numStr(1,28,1)} ${listN.random()}", Label.LabelStyle(font14, Color.valueOf("676767"))).apply {
            setSize(322f, 26f)
//            setAlignment(Align.bottomLeft)
        })
    }

    override fun dispose() {
        generatorRG.dispose()
        font14.dispose()
        vert.dispose()

        super.dispose()
    }

}