package com.pharhaslo.slo7.game.actors

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.pharhaslo.slo7.game.advanced.AdvancedGroup
import com.pharhaslo.slo7.game.assets.SpriteManager
import com.pharhaslo.slo7.game.utils.disable
import com.pharhaslo.slo7.game.utils.setBoundsFigmaY

import com.pharhaslo.slo7.game.utils.ProgressBar as PBar

class ProgressBar: AdvancedGroup() {

    private val grip = Image(SpriteManager.OptionsSprite.PROGRESS_GRIP.data.texture)

    private val progressOnePercent = (PBar.GRIP_MAX_X - PBar.GRIP_MIN_X) / 100f

    var progressBlock: (Int) -> Unit = { }
    var progress = 0
        private set


    init {
        setSize(PBar.W, PBar.H)
        addListener(getMoveListener())
        addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addProgressPanel()
        addProgressGrip()
    }


    private fun addProgressPanel() {
        val image = Image(SpriteManager.OptionsSprite.PROGRESS_PANEL.data.texture).apply {
            disable()
        }
        addAndFillActor(image)
    }

    private fun addProgressGrip() {
        grip.apply {
            disable()
            setBoundsFigmaY(PBar.GRIP_MIN_X, PBar.GRIP_Y, PBar.GRIP_W, PBar.GRIP_H, PBar.H)
        }
        addActor(grip)
    }



    private fun getMoveListener() = object : InputListener() {

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            when {
                x < PBar.GRIP_MIN_X -> {
                    grip.x = PBar.GRIP_MIN_X
                    progress = 0
                }
                x > PBar.GRIP_MAX_X -> {
                    grip.x = PBar.GRIP_MAX_X
                    progress = 100
                }
                else                -> {
                    grip.x = x
                    progress = (x / progressOnePercent).toInt()
                }
            }
            progressBlock(progress)
        }
    }



    fun setProgress(progressValue: Int) {
        when {
            progressValue <= 0   -> {
                grip.x = PBar.GRIP_MIN_X
                progress = 0
            }
            progressValue >= 100 -> {
                grip.x = PBar.GRIP_MAX_X
                progress = 100
            }
            else                 -> {
                progress = progressValue
                grip.x = (progressOnePercent * progressValue)
            }
        }
        progressBlock(progress)
    }

}