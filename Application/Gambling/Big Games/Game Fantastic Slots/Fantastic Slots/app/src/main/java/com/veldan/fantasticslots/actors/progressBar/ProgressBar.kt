package com.veldan.fantasticslots.actors.progressBar

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.fantasticslots.actors.mask.Mask
import com.veldan.fantasticslots.advanced.AbstractAdvancedGroup
import com.veldan.fantasticslots.assets.SpriteManager
import com.veldan.fantasticslots.utils.disable
import com.veldan.fantasticslots.utils.layout.setBoundsFigmaY

import com.veldan.fantasticslots.utils.ProgressBar as PBar

class ProgressBar: AbstractAdvancedGroup() {
    override val controller = ProgressBarController(this)

    private val panelImage      = Image(    SpriteManager.OptionsSprite.PROGRESS_BAR_PANEL.data.texture     )
    private val progressImage   = Image(    SpriteManager.OptionsSprite.PROGRESS_BAR_PROGRESS.data.texture  )
    private val controllerImage = Image(    SpriteManager.OptionsSprite.PROGRESS_BAR_CONTROLLER.data.texture)
    private val panelMaskGroup  = Mask(SpriteManager.OptionsSprite.PROGRESS_BAR_PROGRESS.data.texture     )

    private val progressOnePercent = (PBar.CONTROLLER_MAX_X - PBar.CONTROLLER_MIN_X) / 100f
    private val halfControllerW    = PBar.CONTROLLER_W / 2

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
        addProgressController()
    }


    private fun addProgressPanel() {
        addActor(panelImage)
        panelImage.apply {
            disable()
            setBoundsFigmaY(PBar.PANEL_X, PBar.PANEL_Y, PBar.PANEL_W, PBar.PANEL_H, PBar.H)
        }

        addActor(panelMaskGroup)
        panelMaskGroup.apply {
            disable()
            setBoundsFigmaY(PBar.PANEL_X, PBar.PANEL_Y, PBar.PANEL_W, PBar.PANEL_H, PBar.H)

            addAndFillActor(progressImage)
            progressImage.x = PBar.CONTROLLER_MIN_X - PBar.PANEL_W + halfControllerW
        }
    }

    private fun addProgressController() {
        addActor(controllerImage)
        controllerImage.apply {
            disable()
            setBoundsFigmaY(PBar.CONTROLLER_MIN_X, PBar.CONTROLLER_Y, PBar.CONTROLLER_W, PBar.CONTROLLER_H, PBar.H)
        }
    }



    private fun getMoveListener() = object : InputListener() {

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            when {
                x < PBar.CONTROLLER_MIN_X -> {
                    progressImage.x   = PBar.CONTROLLER_MIN_X - PBar.PANEL_W + halfControllerW
                    controllerImage.x = PBar.CONTROLLER_MIN_X
                    progress          = 0
                }
                x > PBar.CONTROLLER_MAX_X -> {
                    progressImage.x   = PBar.CONTROLLER_MAX_X - PBar.PANEL_W + halfControllerW
                    controllerImage.x = PBar.CONTROLLER_MAX_X
                    progress          = 100
                }
                else                -> {
                    progressImage.x   = x - PBar.PANEL_W + halfControllerW
                    controllerImage.x = x
                    (x / progressOnePercent).toInt().also { progress = if(it < 0) 0 else it }
                }
            }
            progressBlock(progress)
        }
    }



    fun setProgress(progressValue: Int) {
        when {
            progressValue <= 0   -> {
                progressImage.x   = PBar.CONTROLLER_MIN_X - PBar.PANEL_W + halfControllerW
                controllerImage.x = PBar.CONTROLLER_MIN_X
                progress          = 0
            }
            progressValue >= 100 -> {
                progressImage.x   = PBar.CONTROLLER_MAX_X - PBar.PANEL_W + halfControllerW
                controllerImage.x = PBar.CONTROLLER_MAX_X
                progress          = 100
            }
            else                 -> {
                progressImage.x   = (progressOnePercent * progressValue) - PBar.PANEL_W + halfControllerW
                controllerImage.x = (progressOnePercent * progressValue)
                progress          = progressValue
            }
        }
        progressBlock(progress)
    }

}