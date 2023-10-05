package monska.makkers.conver.currinci.game.actors.scroll

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import monska.makkers.conver.currinci.game.screens.valuta
import monska.makkers.conver.currinci.game.utils.GameColor
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedGroup
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedScreen

class ValutaScrollPane(override val screen: AdvancedScreen, font: BitmapFont) : AdvancedGroup() {

    private val verticalGroup = VerticalGroup(screen,17f,alignment = VerticalGroup.Alignment.TOP,direction = VerticalGroup.Direction.DOWN)
    private val scrollPane    = ScrollPane(verticalGroup)

    private val ls = Label.LabelStyle(font, GameColor.devid)

    override fun addActorsOnGroup() {
        addAndFillActor(scrollPane)

        screen.game.activity.lottie.showLoader()
        valuta?.quotes?.onEach {
            verticalGroup.addActor(
                ValutaGroup(screen, it.key, it.value, ls).apply { setSize(550f, 103f) }
            )
        }
        screen.game.activity.lottie.hideLoader()
    }

    override fun dispose() {
        super.dispose()
        verticalGroup.dispose()
    }

}