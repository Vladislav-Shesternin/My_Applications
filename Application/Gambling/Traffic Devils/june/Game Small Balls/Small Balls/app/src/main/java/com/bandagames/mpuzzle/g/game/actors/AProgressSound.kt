package com.bandagames.mpuzzle.g.game.actors

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.bandagames.mpuzzle.g.game.utils.HEIGHT_UI
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedGroup
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedScreen
import com.bandagames.mpuzzle.g.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AProgressSound(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 825f

    private val assets = screen.game.Aoll

    private val progressImage   = Image(assets.prorg)
    private val haderImage      = Image(assets.porg)
    private val mask            = Mask(screen, assets.pomaska, alphaHeight = HEIGHT_UI.toInt())

    private val onePercentY = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addMask()
        addActor(haderImage)
        haderImage.setBounds(-27f, 0f, 139f, 139f)

        coroutine?.launch {
            progressPercentFlow.collect { percent ->
                runGDX {
                    progressImage.y = percent * onePercentY - LENGTH
                    haderImage.y = (progressImage.y + progressImage.height) - 69f
                }
            }
        }

        addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addMask() {
        addAndFillActor(mask)
        mask.addAndFillActor(progressImage)
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