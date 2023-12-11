package lycky.fortune.tiger.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import lycky.fortune.tiger.game.actors.masks.Mask
import lycky.fortune.tiger.game.utils.advanced.AdvancedGroup
import lycky.fortune.tiger.game.utils.advanced.AdvancedScreen
import lycky.fortune.tiger.game.utils.runGDX

class ATigerLoader(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 1015f

    private val backgroundImage = Image(screen.game.splashAssets.tiger_loader)
    private val progressImage   = Image(screen.game.splashAssets.tiger_progrress)
    private val mask            = Mask(screen, screen.game.splashAssets.TIGER_MASK, alphaWidth = 500)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    private val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addTigerBack()
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

    private fun AdvancedGroup.addTigerBack() {
        addAndFillActor(backgroundImage)
//        backgroundImage.setBounds(0f, 28.8f, 992f, 93.8f)
    }

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(16f, 12f, 1015f, 89f)
        mask.addLoading()
    }

    private fun AdvancedGroup.addLoading() {
        addActor(progressImage)
        progressImage.setBounds(-LENGTH, 0f, LENGTH, 89f)
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