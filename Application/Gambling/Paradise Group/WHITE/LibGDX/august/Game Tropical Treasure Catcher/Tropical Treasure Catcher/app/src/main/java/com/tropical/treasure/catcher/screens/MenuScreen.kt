package com.tropical.treasure.catcher.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.viewport.FitViewport
import com.tropical.treasure.catcher.HEIGHT
import com.tropical.treasure.catcher.WIDTH
import com.tropical.treasure.catcher.actors.ButtonClickable
import com.tropical.treasure.catcher.advanced.AdvancedScreen
import com.tropical.treasure.catcher.assets.SpriteManager
import com.tropical.treasure.catcher.utils.EXIT_H
import com.tropical.treasure.catcher.utils.EXIT_W
import com.tropical.treasure.catcher.utils.EXIT_X
import com.tropical.treasure.catcher.utils.EXIT_Y
import com.tropical.treasure.catcher.utils.NavigationUtil
import com.tropical.treasure.catcher.utils.PLAY_H
import com.tropical.treasure.catcher.utils.PLAY_W
import com.tropical.treasure.catcher.utils.PLAY_X
import com.tropical.treasure.catcher.utils.PLAY_Y
import com.tropical.treasure.catcher.utils.setBoundsFigmaY

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



    private fun setUpPlay() = ButtonClickable(
        ButtonClickable.Style(
        default = SpriteManager.play_def,
        pressed = SpriteManager.play_press,
    )).apply {
        setBoundsFigmaY(PLAY_X, PLAY_Y, PLAY_W, PLAY_H)
        setOnClickListener { NavigationUtil.navigate(GameScreen(), MenuScreen()) }
    }

    private fun setUpExit() = ButtonClickable(
        ButtonClickable.Style(
        default = SpriteManager.exit_def,
        pressed = SpriteManager.exit_press,
    )).apply {
        setBoundsFigmaY(EXIT_X, EXIT_Y, EXIT_W, EXIT_H)
        setOnClickListener { NavigationUtil.exit() }
    }

}