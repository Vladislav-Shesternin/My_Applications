package lycky.fortune.tiger.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import lycky.fortune.tiger.game.actors.masks.Mask
import lycky.fortune.tiger.game.utils.advanced.AdvancedGroup
import lycky.fortune.tiger.game.utils.advanced.AdvancedScreen
import lycky.fortune.tiger.game.utils.runGDX

class ATimer(override val screen: AdvancedScreen): AdvancedGroup() {

    private val backgroundImage = Image(screen.game.gameAssets.tiger_time)
    private val timerImage      = Image(screen.game.gameAssets.time_progress)
    private val timerIcon       = Image(screen.game.gameAssets.time_clock)
    private val timerMask       = Mask(screen, screen.game.gameAssets.TIME_MASK, 1000)


    override fun addActorsOnGroup() {
        addAndFillActor(backgroundImage)
        addTimerImg()
        addTimerIco()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addTimerImg() {
        addActor(timerMask)
        timerMask.setBounds(20f, 13f, 391f, 89f)
        timerMask.addAndFillActor(timerImage)
        timerImage.setOrigin(Align.center)
    }

    private fun AdvancedGroup.addTimerIco() {
        addActor(timerIcon)
        timerIcon.setBounds(13f, 8f, 100f, 100f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    var isPause = false

    fun startTimer(finishBlock: () -> Unit) {
        coroutine?.launch {
            var time = 20f
            var isStart = true

            while (isActive && time > 0) {
                if (isPause.not()) {
                    delay(1000)
                    --time
                    runGDX {
                        if (isStart) {
                            isStart = false
                            timerImage.addAction(Actions.moveTo(-391f, 0f, time))
                        }
                    }
                } else {
                    isStart = true
                    runGDX { timerImage.clearActions() }
                }
            }

            if (time <= 0) {
                cancel()
                finishBlock()
            }
        }
    }


}