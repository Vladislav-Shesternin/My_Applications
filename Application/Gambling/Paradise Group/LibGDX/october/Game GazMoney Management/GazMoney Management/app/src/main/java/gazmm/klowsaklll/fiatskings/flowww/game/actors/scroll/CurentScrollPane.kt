package gazmm.klowsaklll.fiatskings.flowww.game.actors.scroll

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import gazmm.klowsaklll.fiatskings.flowww.game.screens.curent
import gazmm.klowsaklll.fiatskings.flowww.game.utils.GameColor
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedGroup
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedScreen
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.CharType
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.FontPath
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.setCharacters
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.setLinear
import gazmm.klowsaklll.fiatskings.flowww.game.utils.font.setSize

class CurentScrollPane(override val screen: AdvancedScreen) : AdvancedGroup() {

    private val verticalGroup = VerticalGroup(screen, startGap = 20f, endGap = 20f, alignment = VerticalGroup.Alignment.TOP, direction = VerticalGroup.Direction.DOWN)
    private val scrollPane    = ScrollPane(verticalGroup)

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Semibold))
    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Regular))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font38 = generatorSB.generateFont(parameter.setCharacters(CharType.LATIN).setSize(38))
    private val font30 = generatorSB.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"₽,").setSize(30))
    private val font23 = generatorRG.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"+.").setSize(23))

    private val ls1 = LabelStyle(font38, GameColor.belka)
    private val ls2 = LabelStyle(font30, GameColor.belka)
    private val ls3 = LabelStyle(font23, GameColor.gerka)


    override fun addActorsOnGroup() {
        addAndFillActor(scrollPane)

        curent?.rates?.onEach {
            verticalGroup.addActor(
                CurentItem(screen, it.key, it.value, ls1, ls2, ls3).apply { setSize(550f, 103f) }
            )
        }
    }

    override fun dispose() {
        super.dispose()
        verticalGroup.dispose()
        generatorSB.dispose()
        generatorRG.dispose()
        font38.dispose()
        font30.dispose()
        font23.dispose()
    }

}