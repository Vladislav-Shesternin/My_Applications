package com.cosmo.plinko.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.cosmo.plinko.game.actors.masks.Mask
import com.cosmo.plinko.game.utils.advanced.AdvancedGroup
import com.cosmo.plinko.game.utils.advanced.AdvancedScreen
import com.cosmo.plinko.game.utils.runGDX

class AProgress(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 617f

    private val backgroundImage = Image(screen.game.splashAssets.P_BACKGROUND)
    private val progressImage   = Image(screen.game.splashAssets.PROGRESS)
    private val mask            = Mask(screen, screen.game.splashAssets.MASK, alphaWidth = 1000)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addAndFillActor(backgroundImage)
        addMask()

        coroutine?.launch {
            progressPercentFlow.collect { percent ->
                runGDX { progressImage.x = percent * onePercentX - (722) }
            }
        }

//        addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(8f, 11f, 617.4f, 57.7f)
        mask.addProgressImg()
    }

    private fun AdvancedGroup.addProgressImg() {
        addActor(progressImage)
        progressImage.setBounds(-722f, 0f, 722f, 52f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

//    private fun inputListener() = object : InputListener() {
//        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
//            touchDragged(event, x, y, pointer)
//            return true
//        }
//
//        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
//            progressPercentFlow.value = when {
//                x <= 0 -> 0f
//                x >= LENGTH -> 100f
//                else -> x / onePercentX
//            }
//        }
//    }

    fun setProgressPercent(percent: Float) {
        progressPercentFlow.value = percent
    }


}