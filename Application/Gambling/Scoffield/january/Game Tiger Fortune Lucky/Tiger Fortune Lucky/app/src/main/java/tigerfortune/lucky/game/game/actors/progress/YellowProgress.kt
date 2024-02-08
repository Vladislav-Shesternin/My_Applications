package tigerfortune.lucky.game.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import tigerfortune.lucky.game.game.actors.masks.Mask
import tigerfortune.lucky.game.game.utils.advanced.AdvancedGroup
import tigerfortune.lucky.game.game.utils.advanced.AdvancedScreen
import tigerfortune.lucky.game.game.utils.runGDX

class YellowProgress(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 939f

    private val progressImage = Image(screen.game.loadingAssets.startProgress)
    private val frontImage    = Image(screen.game.loadingAssets.startFront)
    private val mask          = Mask(screen, screen.game.loadingAssets.YellowMasakaj, alphaWidth = 500)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    private val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addMask()
        addFront()

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
        addAndFillActor(mask)
        mask.addProgress()
    }

    private fun AdvancedGroup.addProgress() {
        addActor(progressImage)
        progressImage.setBounds(-LENGTH, 0f, LENGTH, 64f)
    }

    private fun AdvancedGroup.addFront() {
        addActor(frontImage)
        frontImage.setBounds(-3f, -7f, 945f, 78f)
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