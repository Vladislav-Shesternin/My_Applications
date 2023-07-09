package com.vurda.start.game.screens

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.vurda.start.game.actors.button.AButton
import com.vurda.start.game.actors.button.AButtonStyle
import com.vurda.start.game.manager.NavigationManager
import com.vurda.start.game.manager.SpriteManager
import com.vurda.start.game.utils.MAIN_ANIM_SPEED
import com.vurda.start.game.utils.advanced.AdvancedGroup
import com.vurda.start.game.utils.advanced.AdvancedScreen

class MenuScreen: AdvancedScreen() {

    private val play       = AButton(AButtonStyle.aaa)
    private val exit       = AButton(AButtonStyle.bbb)


    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        mainGroup.addAction(Actions.alpha(0f))
        addButtons()
        mainGroup.addAction(Actions.fadeIn(MAIN_ANIM_SPEED))
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addButtons() {
        addActors(play, exit)

        play.setBounds(50f, 358f, 447f, 184f)
        exit.setBounds(1204f, 358f, 447f, 184f)

        play.setOnClickListener { navToGame() }
        exit.setOnClickListener { NavigationManager.exit() }
    }

    private fun navToGame() {
        mainGroup.addAction(Actions.sequence(
            Actions.fadeOut(MAIN_ANIM_SPEED),
            Actions.run { NavigationManager.navigate(GameScreen(), MenuScreen()) }
        ))
    }

}