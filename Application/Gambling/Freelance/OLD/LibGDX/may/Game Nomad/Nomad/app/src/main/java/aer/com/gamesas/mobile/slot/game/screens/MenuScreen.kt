package aer.com.gamesas.mobile.slot.game.screens

import aer.com.gamesas.mobile.slot.game.actors.button.AButton
import aer.com.gamesas.mobile.slot.game.actors.button.AButtonStyle
import aer.com.gamesas.mobile.slot.game.actors.button.AButtonText
import aer.com.gamesas.mobile.slot.game.manager.FontTTFManager
import aer.com.gamesas.mobile.slot.game.manager.NavigationManager
import aer.com.gamesas.mobile.slot.game.manager.SpriteManager
import aer.com.gamesas.mobile.slot.game.utils.Layout
import aer.com.gamesas.mobile.slot.game.utils.actor.setBounds
import aer.com.gamesas.mobile.slot.game.utils.advanced.AdvancedGroup
import aer.com.gamesas.mobile.slot.game.utils.advanced.AdvancedScreen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label

class MenuScreen: AdvancedScreen() {

    private val playBtn  = AButton(AButtonStyle.play)
    private val exitBtn  = AButton(AButtonStyle.exit)



    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addButtons()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addButtons() {
        addActors(playBtn, exitBtn)

        playBtn.apply {
            setBounds(Layout.Menu.play)
            setOnClickListener { NavigationManager.navigate(PlinkoScreen(), MenuScreen()) }
        }
        exitBtn.apply {
            setBounds(Layout.Menu.exit)
            setOnClickListener { NavigationManager.exit() }
        }
    }

}