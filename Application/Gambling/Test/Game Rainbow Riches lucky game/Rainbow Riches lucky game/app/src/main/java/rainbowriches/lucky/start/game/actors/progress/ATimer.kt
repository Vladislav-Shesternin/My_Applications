package rainbowriches.lucky.start.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import rainbowriches.lucky.start.game.actors.masks.Mask
import rainbowriches.lucky.start.game.utils.advanced.AdvancedGroup
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen
import rainbowriches.lucky.start.game.utils.runGDX
import rainbowriches.lucky.start.util.log

class ATimer(override val screen: AdvancedScreen): AdvancedGroup() {

    private val backgroundImage = Image(screen.game.gameAssets.SEASON_FRAME_BACK)
    private val progressImage   = Image(screen.game.gameAssets.SEASON_GREEN)
    private val mask            = Mask(screen, screen.game.gameAssets.SEASON, alphaWidth = 1000)
    private val timerImage      = Image(screen.game.gameAssets.SEASON_TIME)

    var isPause = false

    override fun addActorsOnGroup() {
        addAndFillActor(backgroundImage)
        addMask()
        addTimerImg()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(13f, 18f, 541f, 47f)
        mask.addProgressImg()
    }

    private fun AdvancedGroup.addProgressImg() {
        addActor(progressImage)
        progressImage.setBounds(0f, 0f, 541f, 47f)
    }

    private fun AdvancedGroup.addTimerImg() {
        addActor(timerImage)
        timerImage.setBounds(-18f, -20f, 116f, 122f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun startTimer(finishBlock: () -> Unit) {
        coroutine?.launch {
            var time    = 60f
            var isStart = true

            runGDX { progressImage.addAction(Actions.moveTo(-541f, 0f, time)) }

            while (isActive && time > 0) {
                if (isPause.not()) {
                    delay(1000)
                    --time
//                    val t = (time%60).toInt()
//                    progressLabel.setText("00:${if (t<10) "0$t" else t}")
//                    log("time = $time")
                    if (isStart) {
                        isStart = false
                        runGDX { progressImage.addAction(Actions.moveTo(-541f, 0f, time)) }
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