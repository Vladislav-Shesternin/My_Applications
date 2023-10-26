package com.bettilt.mobile.pt.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bettilt.mobile.pt.game.actors.masks.Mask
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedGroup
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedScreen
import com.bettilt.mobile.pt.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AProgress(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 1274f

    private val progressImage = Image(screen.game.spriteUtil.PROGRESS_INDICATOR)
    private val frameImage    = Image(screen.game.spriteUtil.PROGRESS_FRAME)
    private val mask          = Mask(screen, screen.game.spriteUtil.PROGRESS_MASK, alphaWidth = 400)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addMask()
        addFrameImg()

        coroutine?.launch {
            progressPercentFlow.collect { percent ->
                runGDX {
                    progressImage.x = percent * onePercentX - width
                }
            }
        }

        addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addFrameImg() {
        addAndFillActor(frameImage)
    }

    private fun AdvancedGroup.addMask() {
        addAndFillActor(mask)
        mask.addProgressImg()
    }

    private fun AdvancedGroup.addProgressImg() {
        addAndFillActor(progressImage)
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
                x <= 0 -> 0f
                x >= LENGTH -> 100f
                else -> x / onePercentX
            }
        }
    }

    fun setProgressPercent(percent: Float) {
        progressPercentFlow.value = percent
    }


}