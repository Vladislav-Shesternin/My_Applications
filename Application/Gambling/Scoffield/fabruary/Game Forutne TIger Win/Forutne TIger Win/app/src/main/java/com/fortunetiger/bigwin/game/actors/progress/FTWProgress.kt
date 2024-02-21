package com.fortunetiger.bigwin.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.fortunetiger.bigwin.game.actors.masks.Mask
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedGroup
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedScreen
import com.fortunetiger.bigwin.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FTWProgress(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 500f

    private val progressImg = Image(screen.game.allAssets.valuer)
    private val rombImg = Image(screen.game.allAssets.romb)
    private val mask        = Mask(screen, alphaWidth = 1200)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addMask()
        addRomb()

        coroutine?.launch {
            progressPercentFlow.collect { percent ->
                runGDX {
                    rombImg.x = percent * onePercentX
                    progressImg.x = rombImg.x - LENGTH
                }
            }
        }

        addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(0f, 21f, 549f, 40f)
        mask.addProgress()
    }

    private fun AdvancedGroup.addProgress() {
        addActor(progressImg)
        progressImg.setBounds(-549f, 0f, 549f, 40f)
    }

    private fun AdvancedGroup.addRomb() {
        addActor(rombImg)
        rombImg.setBounds(0f, 0f, 61f, 82f)
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