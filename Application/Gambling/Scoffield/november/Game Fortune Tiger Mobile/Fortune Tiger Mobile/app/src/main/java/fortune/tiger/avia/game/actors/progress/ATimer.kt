package fortune.tiger.avia.game.actors.progress

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import fortune.tiger.avia.game.actors.masks.Mask
import fortune.tiger.avia.game.utils.advanced.AdvancedGroup
import fortune.tiger.avia.game.utils.advanced.AdvancedScreen
import fortune.tiger.avia.game.utils.font.FontParameter
import fortune.tiger.avia.game.utils.runGDX
import fortune.tiger.avia.util.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

class ATimer(override val screen: AdvancedScreen): AdvancedGroup() {

    private val parameter = FontParameter()

    private val backgroundImage = Image(screen.game.gameAssets.PROGRESS_BACKGROUND)
    private val progressImage   = Image(screen.game.gameAssets.PROGRESS)
    private val mask            = Mask(screen, screen.game.gameAssets.PG_MASKA, alphaWidth = 1000)
    private val progressLabel   = Label("00:60", Label.LabelStyle(screen.fontRegular.generateFont(parameter.setCharacters(FontParameter.CharType.NUMBERS.chars+":").setSize(78)), Color.WHITE))

    var isPause = false

    override fun addActorsOnGroup() {
        addAndFillActor(backgroundImage)
        addMask()
        addProgressLbl()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addMask() {
        addActor(mask)
        mask.setBounds(14f, 12f, 407f, 86f)
        mask.addProgressImg()
    }

    private fun AdvancedGroup.addProgressImg() {
        addActor(progressImage)
        progressImage.setBounds(0f, 0f, 407f, 86f)
    }

    private fun AdvancedGroup.addProgressLbl() {
        addActor(progressLabel)
        progressLabel.setBounds(115f, 12f, 228f, 79f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun startTimer(finishBlock: () -> Unit) {
        coroutine?.launch {
            var time    = 60f
            var isStart = true

            runGDX { progressImage.addAction(Actions.moveTo(-407f, 0f, time)) }

            while (isActive && time > 0) {
                if (isPause.not()) {
                    delay(1000)
                    --time
                    val t = (time%60).toInt()
                    progressLabel.setText("00:${if (t<10) "0$t" else t}")
                    log("time = $time")
                    if (isStart) {
                        isStart = false
                        runGDX { progressImage.addAction(Actions.moveTo(-407f, 0f, time)) }
                    }
                } else {
                    isStart = true
                    runGDX { progressImage.clearActions() }
                }
            }
            cancel()
            finishBlock()
        }
    }


}