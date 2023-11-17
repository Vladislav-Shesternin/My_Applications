package aiebu.kakono.tutokazalos

import aiebu.kakono.tutokazalos.soloha.Itema
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.AdvancedGroup
import aiebu.kakono.tutokazalos.soloha.pisoha.front.AdvancedScreen
import aiebu.kakono.tutokazalos.soloha.pisoha.front.CharType
import aiebu.kakono.tutokazalos.soloha.pisoha.front.FontPath
import aiebu.kakono.tutokazalos.soloha.pisoha.front.setCharacters
import aiebu.kakono.tutokazalos.soloha.pisoha.front.setLinear
import aiebu.kakono.tutokazalos.soloha.pisoha.front.setSize
import aiebu.kakono.tutokazalos.soloha.pisoha.numStr
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane

class Scrollkat(override val screen: AdvancedScreen): AdvancedGroup() {

    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.RG))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font35 = generatorRG.generateFont(parameter.setCharacters(CharType.ALL).setSize(35))

    private val vert = VerticalGroup(screen, 28f, alignment = VerticalGroup.Alignment.TOP, direction = VerticalGroup.Direction.DOWN)
    private val srce = ScrollPane(vert)

    private val listN = listOf(
        "Января",
        "Февраля",
        "Сентября",
        "Октября",
        "Ноября",
        "Декабря",
        "Июля",
        "Августа",
    )

    override fun addActorsOnGroup() {
        addAndFillActor(srce)

        repeat((3..7).random()) {
            addLbl()
            repeat((3..7).random()) { vert.addActor(Itema(screen).apply { setSize(636f, 71f) }) }
        }
    }

    private fun addLbl() {
        vert.addActor(Label("${numStr(1,30,1)} ${listN.random()}", Label.LabelStyle(font35, Color.WHITE)).apply { setSize(646f, 26f) })
    }

    override fun dispose() {
        generatorRG.dispose()
        font35.dispose()
        vert.dispose()

        super.dispose()
    }

}