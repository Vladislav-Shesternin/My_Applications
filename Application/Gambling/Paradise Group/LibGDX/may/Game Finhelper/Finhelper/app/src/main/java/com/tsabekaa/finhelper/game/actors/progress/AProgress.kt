package com.tsabekaa.finhelper.game.actors.progress

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.tsabekaa.finhelper.game.actors.masks.normal.Mask
import com.tsabekaa.finhelper.game.manager.SpriteManager
import com.tsabekaa.finhelper.game.utils.Size
import com.tsabekaa.finhelper.game.utils.actor.setBounds
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedGroup
import com.tsabekaa.finhelper.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AProgress: AdvancedGroup() {

    companion object {
        const val progressLength = 536f

        val progressSize = Size(555f, 65f)
        val clickSize    = Size(14f, 65f)
    }

    private val mask          = Mask(SpriteManager.GameRegion.MASAKA.region)
    private val progressImage = Image(SpriteManager.GameRegion.RADUGA.region)
    private val frameImage    = Image(SpriteManager.GameRegion.RAMKA.region)
    private val clickImage    = Image(SpriteManager.GameRegion.PIPKA.region)

    private val onePercentX = progressLength / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(100f)


    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addMask()
        addFrameImage()
        addClickImage()

        coroutine.launch {
            progressPercentFlow.collect { percent ->
                runGDX {
                    clickImage.x    = percent * onePercentX
                    progressImage.x = clickImage.x - progressSize.width
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
        mask.apply {
            setBounds(Vector2(0f, 0f), progressSize)

            addProgressImage()
        }
    }

    private fun AdvancedGroup.addProgressImage() {
        addActor(progressImage)
        progressImage.apply {
            setBounds(Vector2(0f, 0f), progressSize)
        }
    }

    private fun AdvancedGroup.addFrameImage() {
        addActor(frameImage)
        frameImage.apply {
            setBounds(Vector2(0f, 0f), progressSize)
        }
    }


    private fun AdvancedGroup.addClickImage() {
        addActor(clickImage)
        clickImage.apply {
            setBounds(Vector2(0f, 0f), clickSize)
        }
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
                x >= progressLength -> 100f
                else -> x / onePercentX
            }
        }
    }


}