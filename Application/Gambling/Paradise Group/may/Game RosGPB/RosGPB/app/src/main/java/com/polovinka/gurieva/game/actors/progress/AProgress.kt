package com.polovinka.gurieva.game.actors.progress

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.polovinka.gurieva.game.actors.masks.normal.Mask
import com.polovinka.gurieva.game.manager.SpriteManager
import com.polovinka.gurieva.game.utils.Size
import com.polovinka.gurieva.game.utils.actor.enable
import com.polovinka.gurieva.game.utils.actor.setBounds
import com.polovinka.gurieva.game.utils.advanced.AdvancedGroup
import com.polovinka.gurieva.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AProgress: AdvancedGroup() {

    companion object {
        const val progressLength = 886f

        val progressSize = Size(223f, 907f)
        val clickSize    = Size(223f, 21f)
    }

    private val mask          = Mask(SpriteManager.GameRegion.BLACK.region)
    private val progressImage = Image(SpriteManager.GameRegion.GREEN.region)
    private val frameImage    = Image(SpriteManager.GameRegion.WHITE.region)
    private val clickImage    = Image(SpriteManager.GameRegion.RECTANGLE.region)

    private val onePercentY = progressLength / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(100f)


    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addFrameImage()
        addMask()
        addClickImage()

        coroutine.launch {
            progressPercentFlow.collect { percent ->
                runGDX {
                    clickImage.y    = percent * onePercentY
                    progressImage.y = clickImage.y - progressSize.height
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
                y <= 0 -> 0f
                y >= progressLength -> 100f
                else -> y / onePercentY
            }
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {

        }
    }


}