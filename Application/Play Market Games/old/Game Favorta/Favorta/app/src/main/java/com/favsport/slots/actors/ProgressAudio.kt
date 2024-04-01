package com.favsport.slots.actors

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.favsport.slots.advanced.AdvancedGroup
import com.favsport.slots.assets.SpriteManager
import com.favsport.slots.utils.*
import com.favsport.slots.utils.AudioManagerUtil.maxVolumeLevel
import com.favsport.slots.utils.AudioManagerUtil.volumeLevel

class ProgressAudio: AdvancedGroup() {

    companion object {
        const val PROGRESS_ONE_PERCENT = 0.01f
        const val GRIP_PROGRESS_ONE_PERCENT = (PROGRESS_GRIP_MAX / 100f)
    }

    private val volumePercent = volumeLevel / (maxVolumeLevel / 100f)

    private val progressValue = GRIP_PROGRESS_ONE_PERCENT * volumePercent

    private val progressIndicator = Image(SpriteManager.SettingsSprite.PROGRESS.textureData.texture)
    private val progressGrip = Image(SpriteManager.SettingsSprite.PROGRESS_GRIP.textureData.texture)

    var progressBlock: (Float) -> Unit = { }
    var currentVolume: Float = progressValue
        set(value) {
           field = (value / PROGRESS_ONE_PERCENT) * GRIP_PROGRESS_ONE_PERCENT
        }



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addProgressFrame()
        addProgressGrip()
        addProgressIndicator()
    }



    private fun addProgressFrame() {
        val image = Image(SpriteManager.SettingsSprite.PROGRESS_FRAME.textureData.texture).apply {
            setBoundsFigmaY(PROGRESS_FRAME_X, PROGRESS_FRAME_Y, PROGRESS_FRAME_W, PROGRESS_FRAME_H, this@ProgressAudio.height)
            addListener(getMoveListener())
        }
        addActor(image)
    }

    private fun addProgressGrip() {
        val image = progressGrip.apply {
            touchable = Touchable.disabled
            setBoundsFigmaY(currentVolume, PROGRESS_GRIP_Y, PROGRESS_GRIP_W, PROGRESS_GRIP_H, this@ProgressAudio.height)
        }
        addActor(image)
    }

    private fun addProgressIndicator() {
        val image = progressIndicator.apply {
            touchable = Touchable.disabled
            setBoundsFigmaY(PROGRESS_INDICATOR_X, PROGRESS_INDICATOR_Y, currentVolume, PROGRESS_INDICATOR_H, this@ProgressAudio.height)
        }
        addActor(image)
    }



    private fun getMoveListener() = object : InputListener() {

        val onePercent = PROGRESS_GRIP_MAX / 100f

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }
        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            when {
                x < PROGRESS_GRIP_MIN -> {
                    progressIndicator.width = PROGRESS_GRIP_MIN
                    progressGrip.x = PROGRESS_GRIP_MIN
                    progressBlock(0f)
                }
                x > PROGRESS_GRIP_MAX -> {
                    progressIndicator.width = PROGRESS_GRIP_MAX
                    progressGrip.x = PROGRESS_GRIP_MAX
                    progressBlock(1f)
                }
                else -> {
                    progressIndicator.width = x
                    progressGrip.x = x
                    progressBlock((x / onePercent) * PROGRESS_ONE_PERCENT)
                }
            }
        }
    }


}