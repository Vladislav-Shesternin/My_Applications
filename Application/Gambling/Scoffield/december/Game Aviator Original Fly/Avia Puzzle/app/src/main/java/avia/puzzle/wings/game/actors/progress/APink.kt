package avia.puzzle.wings.game.actors.progress

import avia.puzzle.wings.game.actors.masks.Mask
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import avia.puzzle.wings.game.utils.advanced.AdvancedGroup
import avia.puzzle.wings.game.utils.advanced.AdvancedScreen
import avia.puzzle.wings.game.utils.runGDX

class APink(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 342f

    private val progressImage = Image(screen.game.gameAssets.progresska)
    private val cursorImage   = Image(screen.game.gameAssets.cursorro)
    private val mask          = Mask(screen, alphaWidth = 1000)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addMask()
        addCursor()

        coroutine?.launch {
            progressPercentFlow.collect { percent ->
                runGDX {
                    progressImage.x = (percent * onePercentX) - LENGTH
                    cursorImage.x   = (progressImage.x + LENGTH) - 47f
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
        mask.setBounds(0f, 11f, LENGTH, 25f)
        mask.addProgress()
    }

    private fun AdvancedGroup.addProgress() {
        addActor(progressImage)
        progressImage.setBounds(0f, 0f, LENGTH, 25f)
    }

    private fun AdvancedGroup.addCursor() {
        addActor(cursorImage)
        cursorImage.setBounds(0f, 0f, 47f, 47f)
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