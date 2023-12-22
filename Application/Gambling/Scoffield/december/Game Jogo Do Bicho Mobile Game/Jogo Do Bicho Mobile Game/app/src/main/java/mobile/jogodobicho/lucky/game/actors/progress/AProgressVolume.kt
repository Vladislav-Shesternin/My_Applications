package mobile.jogodobicho.lucky.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mobile.jogodobicho.lucky.game.actors.masks.Mask
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedGroup
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedScreen
import mobile.jogodobicho.lucky.game.utils.runGDX

class AProgressVolume(override val screen: AdvancedScreen): AdvancedGroup() {

    private val LENGTH = 348f

    private val backgroundImage = Image(screen.game.gameAssets.volumerka)
    private val progressImage   = Image(screen.game.gameAssets.zelene_pole)
    private val olafImage       = Image(screen.game.gameAssets.olaf)
    private val mask            = Mask(screen, screen.game.gameAssets.VOLUMERKA_MASAKA, alphaWidth = 1000)

    private val onePercentX = LENGTH / 100f

    // 0 .. 100 %
    val progressPercentFlow = MutableStateFlow(0f)


    override fun addActorsOnGroup() {
        addBackground()
        addMask()
        addOfal()

        coroutine?.launch {
            progressPercentFlow.collect { percent ->
                runGDX {
                    progressImage.x = percent * onePercentX - LENGTH
                    olafImage.x = progressImage.x + LENGTH
                }
            }
        }

        addListener(inputListener())
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addBackground() {
        addAndFillActor(backgroundImage)
    }

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(0f, 7.3f, 381f, 35.5f)

        mask.addProgress()
    }

    private fun AdvancedGroup.addProgress() {
        addActor(progressImage)
        progressImage.setBounds(-381f, 0f, 381f, 35.5f)
    }

    private fun AdvancedGroup.addOfal() {
        addActor(olafImage)
        olafImage.setBounds(0f, 0f, 34f, 49f)
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