package com.jettylucketjet1wincasino.onewinslots1win.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.jettylucketjet1wincasino.onewinslots1win.game.actors.button.AButtonStyle
import com.jettylucketjet1wincasino.onewinslots1win.game.actors.button.AButtonText
import com.jettylucketjet1wincasino.onewinslots1win.game.actors.label.ALabelStyle
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.NavigationManager
import com.jettylucketjet1wincasino.onewinslots1win.game.manager.SpriteManager
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.MAIN_ANIM_SPEED
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.actor.setBounds
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.advanced.AdvancedGroup
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.advanced.AdvancedScreen
import com.jettylucketjet1wincasino.onewinslots1win.game.utils.Layout.Menu as LM

class MenuScreen: AdvancedScreen() {

    private val menu   = Image(SpriteManager.GameRegion.MENU.region)
    private val button = AButtonText("Играть", AButtonStyle.btn, ALabelStyle.style(ALabelStyle.Bold._21))


    override fun show() {
        mainGroup.addAction(Actions.alpha(0f))
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND_1.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        mainGroup.addAction(Actions.alpha(0f))
        addMenu()
        addButton()
        mainGroup.addAction(Actions.fadeIn(MAIN_ANIM_SPEED))
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addMenu() {
        addActor(menu)
        menu.setBounds(LM.menu)
    }

    private fun AdvancedGroup.addButton() {
        addActor(button)
        button.setBounds(LM.button)
        button.setOnClickListener { navToGame() }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navToGame() {
        mainGroup.addAction(Actions.sequence(
            Actions.fadeOut(MAIN_ANIM_SPEED),
            Actions.run { NavigationManager.navigate(GameScreen(), MenuScreen()) }
        ))
    }
}