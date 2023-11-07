package uniwersal.pictures.present.game.actors.scroll

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import uniwersal.pictures.present.game.utils.GameColor
import uniwersal.pictures.present.game.utils.advanced.AdvancedGroup
import uniwersal.pictures.present.game.utils.advanced.AdvancedScreen
import uniwersal.pictures.present.game.utils.font.CharType
import uniwersal.pictures.present.game.utils.font.FontPath
import uniwersal.pictures.present.game.utils.font.setCharacters
import uniwersal.pictures.present.game.utils.font.setLinear
import uniwersal.pictures.present.game.utils.font.setSize

class BabloScrollPane(override val screen: AdvancedScreen) : AdvancedGroup() {

    private val verticalGroup = VerticalGroup(screen)
    private val scrollPane    = ScrollPane(verticalGroup)

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Semibold))
    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Regular))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font40 = generatorSB.generateFont(parameter.setCharacters(CharType.LATIN).setSize(40))
    private val font32 = generatorSB.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"R,").setSize(32))
    private val font24 = generatorRG.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"+.").setSize(24))

    private val ls1 = LabelStyle(font40, GameColor.belka)
    private val ls2 = LabelStyle(font32, GameColor.belka)
    private val ls3 = LabelStyle(font24, GameColor.gerka)


    override fun addActorsOnGroup() {
        addAndFillActor(scrollPane)

        repeat((10..40).random()) {
            verticalGroup.addActor(
                BabloItem(screen, ls1, ls2, ls3).apply { setSize(642f, 134f) }
            )
        }
    }

    override fun dispose() {
        super.dispose()
        verticalGroup.dispose()
        generatorSB.dispose()
        generatorRG.dispose()
        font40.dispose()
        font32.dispose()
        font24.dispose()
    }

}