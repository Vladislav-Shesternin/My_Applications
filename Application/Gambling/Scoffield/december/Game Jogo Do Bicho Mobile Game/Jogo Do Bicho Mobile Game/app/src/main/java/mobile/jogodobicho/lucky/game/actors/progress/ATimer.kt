package mobile.jogodobicho.lucky.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import mobile.jogodobicho.lucky.game.actors.masks.Mask
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedGroup
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedScreen
import mobile.jogodobicho.lucky.game.utils.runGDX

class ATimer(override val screen: AdvancedScreen): AdvancedGroup() {

    private val backgroundImage = Image(screen.game.gameAssets.trim_back)
    private val progressImage   = Image(screen.game.gameAssets.trim_prog)
    private val mask            = Mask(screen, screen.game.gameAssets.TRIM_MASK, alphaWidth = 1000)
    private val timerImage      = Image(screen.game.gameAssets.trim_a)

    var isPause = false

    override fun addActorsOnGroup() {
        addBackground()
        addMask()
        addTimerImg()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addBackground() {
        addActor(backgroundImage)
        backgroundImage.setBounds(52f, 5f, 224f, 56f)
    }

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(54f, 8f, 220f, 51f)
        mask.addProgressImg()
    }

    private fun AdvancedGroup.addProgressImg() {
        addActor(progressImage)
        progressImage.setBounds(0f, 0f, 220f, 51f)
    }

    private fun AdvancedGroup.addTimerImg() {
        addActor(timerImage)
        timerImage.setBounds(0f, 0f, 67f, 77f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun startTimer(finishBlock: () -> Unit) {
        coroutine?.launch {
            var time    = 60f
            var isStart = true

            runGDX { progressImage.addAction(Actions.moveTo(-220f, 0f, time)) }

            while (isActive && time > 0) {
                if (isPause.not()) {
                    delay(1000)
                    --time
//                    val t = (time%60).toInt()
//                    progressLabel.setText("00:${if (t<10) "0$t" else t}")
//                    log("time = $time")
                    if (isStart) {
                        isStart = false
                        runGDX { progressImage.addAction(Actions.moveTo(-220f, 0f, time)) }
                    }
                } else {
                    isStart = true
                    runGDX { progressImage.clearActions() }
                }
            }
            if (time <= 0) {
                cancel()
                finishBlock()
            }
        }
    }


}