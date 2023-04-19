package com.veldan.junglego.actors

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.junglego.advanced.AdvancedGroup
import com.veldan.junglego.assets.SpriteManager
import com.veldan.junglego.utils.*

class ProgressBar: AdvancedGroup() {

    private val indicator = Image(SpriteManager.OptionsSprite.PROGRESS_INDICATOR.textureData.texture)
    private val controller = Image(SpriteManager.OptionsSprite.PROGRESS_CONTROLLER.textureData.texture)

    private val progressOnePercent = Options.PROGRESS_BAR_FRAME_H / 100f

    private val controllerMinY = getFigmaY(Options.PROGRESS_BAR_CONTROLLER_MIN_Y, Options.PROGRESS_BAR_CONTROLLER_H, Options.PROGRESS_BAR_H)
    private val controllerMaxY = getFigmaY(Options.PROGRESS_BAR_CONTROLLER_MAX_Y, Options.PROGRESS_BAR_CONTROLLER_H, Options.PROGRESS_BAR_H)

    var progressBlock: (Int) -> Unit = { }
    var progress = 0
        private set


    init {
        setSize(Options.PROGRESS_BAR_W, Options.PROGRESS_BAR_H)
        addActorsOnGroup()
        addListener(getMoveListener())
    }



    private fun addActorsOnGroup() {
        addProgressFrame()
        addProgressIndicator()
        addProgressController()
    }


    private fun addProgressFrame() {
        val image = Image(SpriteManager.OptionsSprite.PROGRESS_FRAME.textureData.texture).apply {
            disable()
            setBoundsFigmaY(Options.PROGRESS_BAR_FRAME_X, Options.PROGRESS_BAR_FRAME_Y, Options.PROGRESS_BAR_FRAME_W, Options.PROGRESS_BAR_FRAME_H, Options.PROGRESS_BAR_H)
        }
        addActor(image)
    }

    private fun addProgressIndicator() {
        val image = indicator.apply {
            disable()
            setBoundsFigmaY(Options.PROGRESS_BAR_INDICATOR_X, Options.PROGRESS_BAR_INDICATOR_Y, Options.PROGRESS_BAR_INDICATOR_W, 1f, Options.PROGRESS_BAR_H)
        }
        addActor(image)
    }

    private fun addProgressController() {
        val image = controller.apply {
            disable()
            setBoundsFigmaY(0f, Options.PROGRESS_BAR_CONTROLLER_MIN_Y, Options.PROGRESS_BAR_CONTROLLER_W, Options.PROGRESS_BAR_CONTROLLER_H, Options.PROGRESS_BAR_H)
        }
        addActor(image)
    }



    private fun getMoveListener() = object : InputListener() {

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            when {
                y < controllerMinY -> {
                    indicator.height = 0f
                    controller.y = controllerMinY
                    progress = 0
                }
                y > controllerMaxY -> {
                    indicator.height = Options.PROGRESS_BAR_INDICATOR_MAX_H
                    controller.y = controllerMaxY
                    progress = 100
                }
                else               -> {
                    indicator.height = y
                    controller.y = y
                    progress = (y / progressOnePercent).toInt()
                }
            }
            progressBlock(progress)
        }
    }



    fun setProgress(progressValue: Int) {
        when {
            progressValue <= 0   -> {
                indicator.height = 0f
                controller.y = controllerMinY
                progress = 0
            }
            progressValue >= 100 -> {
                indicator.height = Options.PROGRESS_BAR_INDICATOR_MAX_H
                controller.y = controllerMaxY
                progress = 100
            }
            else                 -> {
                progress = progressValue
                val newProgress = (progressOnePercent * progressValue)
                controller.y = newProgress
                indicator.height = newProgress
            }
        }
        progressBlock(progress)
    }

}