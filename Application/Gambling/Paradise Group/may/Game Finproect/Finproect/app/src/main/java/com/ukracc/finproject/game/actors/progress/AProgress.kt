package com.ukracc.finproject.game.actors.progress

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.ukracc.finproject.game.actors.masks.normal.Mask
import com.ukracc.finproject.game.manager.SpriteManager
import com.ukracc.finproject.game.utils.Size
import com.ukracc.finproject.game.utils.actor.setBounds
import com.ukracc.finproject.game.utils.advanced.AdvancedGroup
import com.ukracc.finproject.game.utils.runGDX
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AProgress: AdvancedGroup() {

    companion object {
        const val progressLength = 699f

        val progressSize = Size(47f, 788f)
        val clickSize    = Size(89f, 89f)
    }

    private val mask          = Mask(SpriteManager.SettingsRegion.MASK.region)
    private val progressImage = Image(SpriteManager.SettingsRegion.PROGRESS.region)
    private val frameImage    = Image(SpriteManager.SettingsRegion.FRAME.region)
    private val clickImage    = Image(SpriteManager.SettingsRegion.CLICK.region)

    private val onePercentY = progressLength / 100f

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
                    clickImage.y    = percent * onePercentY
                    progressImage.y = clickImage.y - progressSize.height + (clickSize.height / 2f)
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
            setBounds(Vector2(21f, 0f), progressSize)

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
            setBounds(Vector2(21f, 0f), progressSize)
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
    }


}