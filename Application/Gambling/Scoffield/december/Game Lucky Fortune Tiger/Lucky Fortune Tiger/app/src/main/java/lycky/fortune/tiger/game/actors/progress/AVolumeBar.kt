package lycky.fortune.tiger.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import lycky.fortune.tiger.game.actors.TmpGroup
import lycky.fortune.tiger.game.utils.advanced.AdvancedGroup
import lycky.fortune.tiger.game.utils.advanced.AdvancedScreen
import lycky.fortune.tiger.game.utils.runGDX

class AVolumeBar(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 470f

    private val backgroundImage = Image(screen.game.gameAssets.volume_progress)
    private val tmpGroup        = TmpGroup(screen)
    private val gripImage       = Image(screen.game.gameAssets.grip)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addBackground()
        addGrip()

        coroutine?.launch {
            progressPercentFlow.collect { percent ->
                runGDX {
                    gripImage.x = (percent * onePercentX)
                }
            }
        }

        tmpGroup.addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addBackground() {
        addAndFillActor(backgroundImage)
    }

    private fun AdvancedGroup.addGrip() {
        addActor(tmpGroup)
        tmpGroup.setBounds(25f, 12f, LENGTH, 50f)
        tmpGroup.addActor(gripImage)
        gripImage.setBounds(0f, -12f, 46f, 71f)
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