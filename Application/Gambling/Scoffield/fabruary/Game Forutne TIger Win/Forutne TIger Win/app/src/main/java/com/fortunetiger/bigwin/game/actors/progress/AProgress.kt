package com.fortunetiger.bigwin.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.fortunetiger.bigwin.game.actors.masks.Mask
import com.fortunetiger.bigwin.game.utils.GameColor
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedGroup
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedScreen
import com.fortunetiger.bigwin.game.utils.runGDX

class AProgress(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 1179f

    private val backgroundImage = Image(screen.game.loaderAssets.bahanovski)
    private val progressImage   = Image(screen.game.loaderAssets.progressoluter)
    private val mask            = Mask(screen, alphaWidth = 1000)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addAndFillActor(backgroundImage)
        addMask()

        coroutine?.launch {
            progressPercentFlow.collect { percent ->
                runGDX { progressImage.x = percent * onePercentX - LENGTH }
            }
        }

//        addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(51f, 47f, 1179f, 82f)
        mask.addProgress()
    }

    private fun AdvancedGroup.addProgress() {
        addActor(progressImage)
        progressImage.setBounds(-LENGTH, 0f, LENGTH, 82f)
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