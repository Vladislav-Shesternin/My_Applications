package com.veldan.icecasino.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.icecasino.HEIGHT
import com.veldan.icecasino.WIDTH
import com.veldan.icecasino.actors.ButtonClickable
import com.veldan.icecasino.advanced.AdvancedScreen
import com.veldan.icecasino.assets.SpriteManager
import com.veldan.icecasino.utils.*

class MenuScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)



    override fun show() {
        super.show()
        background = SpriteManager.menu_background
        stage.addActors(getActors())
    }



    private fun getActors() = listOf<Actor>(
        setUpPlay(),
        setUpExit(),
    )



    private fun setUpPlay() = ButtonClickable(ButtonClickable.Style(
        default = SpriteManager.play_def,
        pressed = SpriteManager.play_press,
    )).apply {
        setBoundsFigmaY(PLAY_X, PLAY_Y, PLAY_W, PLAY_H)
        setOnClickListener { NavigationUtil.navigate(GameScreen(), MenuScreen()) }
    }

    private fun setUpExit() = ButtonClickable(ButtonClickable.Style(
        default = SpriteManager.exit_def,
        pressed = SpriteManager.exit_press,
    )).apply {
        setBoundsFigmaY(EXIT_X, EXIT_Y, EXIT_W, EXIT_H)
        setOnClickListener { NavigationUtil.exit() }
    }

}