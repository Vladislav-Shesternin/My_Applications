package fortunetiger.you.luck.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import fortunetiger.you.luck.game.actors.masks.Mask
import fortunetiger.you.luck.game.utils.advanced.AdvancedGroup
import fortunetiger.you.luck.game.utils.advanced.AdvancedScreen
import fortunetiger.you.luck.game.utils.runGDX

class ALoading(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 958f

    private val progressImage  = Image(screen.game.splashAssets.LOA_LOAD)
    private val mask           = Mask(screen, screen.game.splashAssets.LOA_MASK, alphaWidth = 1000)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
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
        addAndFillActor(mask)
        mask.addLoading()
    }

    private fun AdvancedGroup.addLoading() {
        addActor(progressImage)
        progressImage.setBounds(-LENGTH, 0f, LENGTH, 62f)
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