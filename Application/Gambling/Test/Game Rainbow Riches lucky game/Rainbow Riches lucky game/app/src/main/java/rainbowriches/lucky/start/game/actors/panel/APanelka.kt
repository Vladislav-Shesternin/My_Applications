package rainbowriches.lucky.start.game.actors.panel

import com.badlogic.gdx.scenes.scene2d.ui.Image
import rainbowriches.lucky.start.game.utils.actor.setOnClickListener
import rainbowriches.lucky.start.game.utils.advanced.AdvancedGroup
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen

class APanelka(
    override val screen: AdvancedScreen,
    val type: Static.Type,
): AdvancedGroup() {

    private val panelImg = Image(screen.game.gameAssets.PANEL_GREEN)
    private val xrestImg = Image(screen.game.gameAssets.X)
    private val typeImg  = Image(getRegionByType())


    override fun addActorsOnGroup() {
        addAndFillActor(panelImg)
        addXrest()
        addType()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addXrest() {
        addActor(xrestImg)
        xrestImg.apply {
            setBounds(729f, 11f, 201f, 206f)
            setOnClickListener(screen.game.soundUtil) { screen.animHideScreen { screen.game.navigationManager.back() } }
        }
    }

    private fun addType() {
        addActor(typeImg)
        typeImg.apply {
            setBounds(169f, 883f, 553f, 226f)
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun getRegionByType() = when(type) {
        Static.Type.LEVEL    -> screen.game.gameAssets.LEVEL
        Static.Type.RULES    -> screen.game.gameAssets.RULES
        Static.Type.SETTINGS -> screen.game.gameAssets.SETTINGS
    }

    object Static {
        enum class Type {
            LEVEL, RULES, SETTINGS
        }
    }

}