package com.roshevasternin.rozval.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.roshevasternin.rozval.game.actors.mask.Mask
import com.roshevasternin.rozval.game.utils.advanced.AdvancedGroup
import com.roshevasternin.rozval.game.utils.advanced.AdvancedScreen
import com.roshevasternin.rozval.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AProgress(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 719f

    private val backgroundImage = Image(screen.game.assetsAll.progress_background)
    private val progressImage   = Image(screen.game.assetsAll.progress_progress)
    private val armImage        = Image(screen.game.assetsAll.progress_arm)
    private val mask            = Mask(screen, screen.game.assetsAll.PROGRESS_MASK, alphaHeight = 700)

    private val onePercentY = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addBackground()
        addMask()
        addArm()

        coroutine?.launch {
            progressPercentFlow.collect { percent ->
                runGDX {
                    armImage.y      = percent * onePercentY
                    progressImage.y = (armImage.y - LENGTH + 8)
                }
            }
        }

        addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addBackground() {
        addActor(backgroundImage)
        backgroundImage.setBounds(33f, 0f, 10f, 736f)
    }

    private fun AdvancedGroup.addArm() {
        addActor(armImage)
        armImage.setBounds(0f, 0f, 76f, 17f)
    }

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(34f, 1f, 8f, 726f)
        mask.addProgress()
    }

    private fun AdvancedGroup.addProgress() {
        addActor(progressImage)
        progressImage.setBounds(0f, 0f, 8f, 726f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun inputListener() = object : InputListener() {
        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchDragged(event, x, y, pointer)
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            progressPercentFlow.value = when {
                y <= 0 -> 0f
                y >= LENGTH -> 100f
                else -> y / onePercentY
            }

            event?.stop()
        }
    }

    fun setProgressPercent(percent: Float) {
        progressPercentFlow.value = percent
    }


}