package aviator.original.win.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import aviator.original.win.game.actors.masks.Mask
import aviator.original.win.game.utils.advanced.AdvancedGroup
import aviator.original.win.game.utils.advanced.AdvancedScreen
import aviator.original.win.game.utils.runGDX

class AProgressBar(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 495f

    private val progressImage = Image(screen.game.splashAssets.progress)
    private val circulImage   = Image(screen.game.splashAssets.circul)
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
                    progressImage.x = percent * onePercentX - LENGTH
                    circulImage.x   = progressImage.x + LENGTH - 5f
                }
            }
        }

//        addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(0f, 14f, LENGTH, 15f)
        mask.addProgress()
    }

    private fun AdvancedGroup.addProgress() {
        addActor(progressImage)
        progressImage.setBounds(-LENGTH, 0f, LENGTH, 15f)
    }

    private fun AdvancedGroup.addCursor() {
        addActor(circulImage)
        circulImage.setBounds(0f, 0f, 27f, 37f)
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