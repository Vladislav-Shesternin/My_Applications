package com.toy.land.happy.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.viewport.FitViewport
import com.toy.land.happy.HEIGHT
import com.toy.land.happy.WIDTH
import com.toy.land.happy.actors.ButtonClickable
import com.toy.land.happy.advanced.AdvancedScreen
import com.toy.land.happy.assets.SpriteManager
import com.toy.land.happy.utils.EXIT_H
import com.toy.land.happy.utils.EXIT_W
import com.toy.land.happy.utils.EXIT_X
import com.toy.land.happy.utils.EXIT_Y
import com.toy.land.happy.utils.NavigationUtil
import com.toy.land.happy.utils.PLAY_H
import com.toy.land.happy.utils.PLAY_W
import com.toy.land.happy.utils.PLAY_X
import com.toy.land.happy.utils.PLAY_Y
import com.toy.land.happy.utils.setBoundsFigmaY

class MenuScreen : AdvancedScreen() {
    override val viewport = FitViewport(WIDTH, HEIGHT)



    override fun show() {
        super.show()
       // background = SpriteManager.menu_background
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