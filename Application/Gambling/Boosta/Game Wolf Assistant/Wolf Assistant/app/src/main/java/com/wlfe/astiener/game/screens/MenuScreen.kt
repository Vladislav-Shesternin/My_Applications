package com.wlfe.astiener.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.wlfe.astiener.game.actors.checkbox.CheckBox
import com.wlfe.astiener.game.actors.checkbox.CheckBoxStyle
import com.wlfe.astiener.game.manager.NavigationManager
import com.wlfe.astiener.game.manager.SpriteManager
import com.wlfe.astiener.game.util.advanced.AdvancedScreen
import com.wlfe.astiener.game.util.advanced.AdvancedStage

class MenuScreen: AdvancedScreen() {

    private val btns = Image(SpriteManager.GameRegion.BTNS.region)
    private val play = Actor()
    private val exit = Actor()
    private val musicCB = CheckBox(CheckBoxStyle.music)




    override fun show() {
        setBackgrounds(SpriteManager.GameRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStage() {
        addButtonS()
        addPlay()
        addExit()
        addMusicCB()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addButtonS() {
        addActor(btns)
        btns.apply {
            setBounds(471f, 64f, 495f, 537f)
        }

    }

    private fun AdvancedStage.addPlay() {
        addActor(play)
        play.apply {
            setBounds(484f, 393f, 469f, 208f)
            setOnClickListener {
                NavigationManager.navigate(GameScreen(), MenuScreen())
            }
        }

    }

    private fun AdvancedStage.addMusicCB() {
        addActor(musicCB)
        musicCB.setBounds(542f, 226f, 352f, 143f)

        musicUtil?.music?.let { mmm ->
            if (mmm.isPlaying) musicCB.check()

            musicCB.setOnCheckListener { if (it) mmm.play() else mmm.pause() }
        }
    }

    private fun AdvancedStage.addExit() {
        addActor(exit)
        exit.apply {
            setBounds(569f, 81f, 298f, 121f)
            setOnClickListener { NavigationManager.exit() }
        }

    }

}

fun Actor.setOnClickListener(block: (Actor) -> Unit) {
    addListener(object : InputListener() {
        var isWithin = false

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            isWithin = x in 0f..width && y in 0f..height
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            if (isWithin) {
                isWithin = false
                block(this@setOnClickListener)
            }
        }
    })
}