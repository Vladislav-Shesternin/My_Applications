package rainbowriches.lucky.start.game.actors.panel

import com.badlogic.gdx.scenes.scene2d.ui.Image
import rainbowriches.lucky.start.game.screens.EasyNormalHardScreen
import rainbowriches.lucky.start.game.utils.actor.setOnClickListener
import rainbowriches.lucky.start.game.utils.advanced.AdvancedGroup
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen

var Level = LEVEL.EASY
class APanelLevels(override val screen: AdvancedScreen): AdvancedGroup() {

    private val panelImg  = APanelka(screen, APanelka.Static.Type.LEVEL)
    private val btns      = listOf(
        Image(screen.game.gameAssets.L_EASY),
        Image(screen.game.gameAssets.L_NORMAL),
        Image(screen.game.gameAssets.L_HARD),
    )

    override fun addActorsOnGroup() {
        addAndFillActor(panelImg)
        addBtns()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addBtns() {
        val yyy = listOf(662f, 423f, 183f)
        btns.onEachIndexed { index, btn ->
            addActor(btn)
            btn.setBounds(229f, yyy[index], 435f, 178f)
            btn.setOnClickListener(screen.game.soundUtil) {
                Level = when(index) {
                    0 -> LEVEL.EASY
                    1 -> LEVEL.NORMAL
                    2 -> LEVEL.HARD
                    else -> LEVEL.EASY
                }
                screen.animHideScreen { screen.game.navigationManager.navigate(EasyNormalHardScreen::class.java.name, screen::class.java.name) }
            }
        }
    }

}

enum class LEVEL {
    EASY, NORMAL, HARD
}