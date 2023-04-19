package com.veldan.spinia.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.viewport.FitViewport
import com.veldan.spinia.HEIGHT
import com.veldan.spinia.WIDTH
import com.veldan.spinia.actors.ButtonClickable
import com.veldan.spinia.advanced.AdvancedScreen
import com.veldan.spinia.assets.SpriteManager
import com.veldan.spinia.utils.*

class MenuScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)



    override fun show() {
        super.show()
        background = SpriteManager.backgroundList[0]
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