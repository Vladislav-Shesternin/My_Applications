package rainbowriches.lucky.start.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import rainbowriches.lucky.start.game.actors.masks.Mask
import rainbowriches.lucky.start.game.utils.advanced.AdvancedGroup
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen
import rainbowriches.lucky.start.game.utils.runGDX

class ALoading(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 958f

    private val backgroundImage = Image(screen.game.splashAssets.OUTSIDE_BAR)
    private val progressImage   = Image(screen.game.splashAssets.FRONT_BAR)
    private val cursorImage     = Image(screen.game.splashAssets.CURSOR)
    private val mask            = Mask(screen, screen.game.splashAssets.BLACKWOOD, alphaWidth = 1000)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addOut()
        addMask()
        addCursor()

        coroutine?.launch {
            progressPercentFlow.collect { percent ->
                runGDX {
                    progressImage.x = percent * onePercentX - LENGTH
                    cursorImage.x   = (progressImage.x + LENGTH) - (cursorImage.width / 2f)
                }
            }
        }

//        addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addOut() {
        addActor(backgroundImage)
        backgroundImage.setBounds(0f, 28.8f, 992f, 93.8f)
    }

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(17f, 44f, 958f, 62f)
        mask.addLoading()
    }

    private fun AdvancedGroup.addLoading() {
        addActor(progressImage)
        progressImage.setBounds(-LENGTH, 0f, LENGTH, 62f)
    }

    private fun AdvancedGroup.addCursor() {
        addActor(cursorImage)
        cursorImage.setBounds(0f, 13.5f, 126f, 126f)
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