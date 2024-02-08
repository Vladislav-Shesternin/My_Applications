package fortunetiger.you.luck.game.actors.progress

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import fortunetiger.you.luck.game.actors.masks.Mask
import fortunetiger.you.luck.game.utils.advanced.AdvancedGroup
import fortunetiger.you.luck.game.utils.advanced.AdvancedScreen
import fortunetiger.you.luck.game.utils.runGDX
import fortunetiger.you.luck.util.log

class ATimer(override val screen: AdvancedScreen): AdvancedGroup() {

    private val backgroundImage = Image(screen.game.gameAssets.panal)
    private val progressImage   = Image(screen.game.gameAssets.timi_terner)
    private val mask            = Mask(screen, screen.game.gameAssets.TIMR_MASKR, alphaWidth = 1000)
    private val timerImage      = Image(screen.game.gameAssets.tim_ic)

    var isPause = false

    override fun addActorsOnGroup() {
        addBackgroundImg()
        addMask()
        addTimerImg()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addBackgroundImg() {
        addActor(backgroundImage)
        backgroundImage.setBounds(18f, 11f, 648f, 72f)
    }

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(28f, 22f, 627f, 51f)
        mask.addProgressImg()
    }

    private fun AdvancedGroup.addProgressImg() {
        addAndFillActor(progressImage)
    }

    private fun AdvancedGroup.addTimerImg() {
        addActor(timerImage)
        timerImage.setBounds(0f, 0f, 90f, 90f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun startTimer(finishBlock: () -> Unit) {
        coroutine?.launch {
            var time    = 60f
            var isStart = true

            runGDX { progressImage.addAction(Actions.moveTo(-627f, 0f, time)) }

            while (isActive && time > 0) {
                if (isPause.not()) {
                    delay(1000)
                    --time
//                    val t = (time%60).toInt()
//                    progressLabel.setText("00:${if (t<10) "0$t" else t}")
//                    log("time = $time")
                    if (isStart) {
                        isStart = false
                        runGDX { progressImage.addAction(Actions.moveTo(-627f, 0f, time)) }
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