package italodisco.fernando.lucherano.game.manager.util

import italodisco.fernando.lucherano.game.OvochevaVapikanka
import italodisco.fernando.lucherano.game.VerticalGroup
import italodisco.fernando.lucherano.game.utils.advanced.AdvancedGroup
import italodisco.fernando.lucherano.game.utils.advanced.AdvancedScreen
import italodisco.fernando.lucherano.game.utils.font.CharType
import italodisco.fernando.lucherano.game.utils.font.FontPath
import italodisco.fernando.lucherano.game.utils.font.setCharacters
import italodisco.fernando.lucherano.game.utils.font.setLinear
import italodisco.fernando.lucherano.game.utils.font.setSize
import italodisco.fernando.lucherano.game.utils.numStr
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align

class DomiLai(override val screen: AdvancedScreen): AdvancedGroup() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font23 = generatorSB.generateFont(parameter.setCharacters(CharType.ALL).setSize(23))

    private val vert = VerticalGroup(screen, startGap = 0f, gap = 16f, alignment = VerticalGroup.Alignment.TOP, direction = VerticalGroup.Direction.DOWN)
    private val sill = ScrollPane(vert)

    private val poriRoYear = listOf(
        "Декабря",
        "Сентября",
        "Октября",
        "Августа",
        "Января",
        "Ноября",
        "Июля",
        "Февраля",
    )

    override fun addActorsOnGroup() {
        addAndFillActor(sill)

        repeat((2..6).random()) {
            addLbl()
            repeat((2..6).random()) { vert.addActor(OvochevaVapikanka(screen).apply { setSize(524f, 73f) }) }

            val a = Actor().apply {
                width = 7f
                height = 7f
            }
            vert.addActor(a)
        }
    }

    private fun addLbl() {
        vert.addActor(Label("${numStr(1,31,1)} ${poriRoYear.random()}", Label.LabelStyle(font23, Color.valueOf("676768"))).apply {
            setSize(126f, 26f)
            setAlignment(Align.topLeft)
        })
    }

    override fun dispose() {
        generatorSB.dispose()
        font23.dispose()
        vert.dispose()

        super.dispose()
    }

    fun uprugaPopka() {
        vert.clearChildren()

        repeat((2..6).random()) {
            addLbl()
            repeat((2..6).random()) { vert.addActor(OvochevaVapikanka(screen).apply { setSize(524f, 73f) }) }

            val a = Actor().apply {
                width = 7f
                height = 7f
            }
            vert.addActor(a)
        }
    }

}