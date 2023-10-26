package investgroup.program.gaz

import investgroup.program.gaz.game.Itema
import investgroup.program.gaz.game.VerticalGroup
import investgroup.program.gaz.game.utils.advanced.AdvancedGroup
import investgroup.program.gaz.game.utils.advanced.AdvancedScreen
import investgroup.program.gaz.game.utils.font.CharType
import investgroup.program.gaz.game.utils.font.FontPath
import investgroup.program.gaz.game.utils.font.setCharacters
import investgroup.program.gaz.game.utils.font.setLinear
import investgroup.program.gaz.game.utils.font.setSize
import investgroup.program.gaz.game.utils.numStr
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align

class Spiral(override val screen: AdvancedScreen): AdvancedGroup() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font27 = generatorSB.generateFont(parameter.setCharacters(CharType.ALL).setSize(27))

    private val vert = VerticalGroup(screen, startGap = 0f, gap = 29f, alignment = VerticalGroup.Alignment.TOP, direction = VerticalGroup.Direction.DOWN)
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

        repeat((2..7).random()) {
            addLbl()
            repeat((2..7).random()) { vert.addActor(Itema(screen).apply { setSize(626f, 88f) }) }
        }
    }

    private fun addLbl() {
        vert.addActor(Label("${numStr(1,28,1)} ${listN.random()}", Label.LabelStyle(font27, Color.BLACK)).apply {
            setSize(615f, 49f)
            setAlignment(Align.bottomLeft)
        })
    }

    override fun dispose() {
        generatorSB.dispose()
        font27.dispose()
        vert.dispose()

        super.dispose()
    }

}