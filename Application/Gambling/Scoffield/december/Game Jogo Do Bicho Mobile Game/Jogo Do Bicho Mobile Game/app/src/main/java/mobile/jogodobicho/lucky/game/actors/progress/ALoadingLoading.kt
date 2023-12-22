package mobile.jogodobicho.lucky.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mobile.jogodobicho.lucky.game.actors.masks.Mask
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedGroup
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedScreen
import mobile.jogodobicho.lucky.game.utils.runGDX
import mobile.jogodobicho.lucky.util.log

class ALoadingLoading(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 518f

    private val backgroundImage = Image(screen.game.splashAssets.liading_liading)
    private val progressImage   = Image(screen.game.splashAssets.progigress)
    private val mask            = Mask(screen, screen.game.splashAssets.PROGIGRESS_MASKARADJA, alphaWidth = 1000)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addBackground()
        addMask()

        coroutine?.launch {
            progressPercentFlow.collect { percent ->
                runGDX {
                    progressImage.x = percent * onePercentX - LENGTH
                }
            }
        }

//        addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addBackground() {
        addAndFillActor(backgroundImage)
    }

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(205f, 55f, LENGTH, 49f)
        mask.addProgress()
    }

    private fun AdvancedGroup.addProgress() {
        addActor(progressImage)
        progressImage.setBounds(-LENGTH, 0f, LENGTH, 49f)
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