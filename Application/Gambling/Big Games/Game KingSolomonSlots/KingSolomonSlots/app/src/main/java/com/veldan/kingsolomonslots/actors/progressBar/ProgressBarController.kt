package com.veldan.kingsolomonslots.actors.progressBar

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.veldan.kingsolomonslots.utils.controller.GroupController
import com.veldan.kingsolomonslots.utils.log
import com.veldan.kingsolomonslots.layout.Layout.ProgressBar as LP

class ProgressBarController(override val group: ProgressBar) : GroupController {

    private val progressOnePercent = (LP.CONTROLLER_MAX_X - LP.CONTROLLER_MIN_X) / 100f
    private var progress           = 0
    private var progressLength     = LP.PROGRESS_W

    var progressBlock: (Int) -> Unit = { }





    fun getMoveListener() = object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            with(group) {
                when {
                    x < LP.CONTROLLER_MIN_X -> {
                        progressImage.x   = LP.CONTROLLER_MIN_X - progressLength
                        controllerImage.x = LP.CONTROLLER_MIN_X
                        progress = 0
                    }
                    x > LP.CONTROLLER_MAX_X -> {
                        progressImage.x   = LP.CONTROLLER_MAX_X - progressLength
                        controllerImage.x = LP.CONTROLLER_MAX_X
                        progress = 100
                    }
                    else                    -> {
                        progressImage.x   = x - progressLength
                        controllerImage.x = x
                        (x / progressOnePercent).toInt().also { progress = if (it < 0) 0 else it }
                    }
                }
            }
            progressBlock(progress)
        }
    }

    fun setProgress(progressValue: Int) {
        with(group) {
            when {
                progressValue <= 0   -> {
                    progressImage.x   = LP.CONTROLLER_MIN_X - progressLength
                    controllerImage.x = LP.CONTROLLER_MIN_X
                    progress          = 0
                }
                progressValue >= 100 -> {
                    progressImage.x   = LP.CONTROLLER_MAX_X - progressLength
                    controllerImage.x = LP.CONTROLLER_MAX_X
                    progress          = 100
                }
                else                 -> {
                    progressImage.x   = (progressOnePercent * progressValue) - progressLength
                    controllerImage.x = (progressOnePercent * progressValue)
                    progress          = progressValue
                }
            }
        }
        progressBlock(progress)
    }

}