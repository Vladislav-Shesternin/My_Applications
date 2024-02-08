package avia.puzzle.wings.game.actors

import avia.puzzle.wings.game.utils.advanced.AdvancedGroup
import avia.puzzle.wings.game.utils.advanced.AdvancedScreen
import avia.puzzle.wings.game.utils.font.FontParameter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ATimer(override val screen: AdvancedScreen): AdvancedGroup() {

    private val params = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + ":").setSize(40)
    private val font   = screen.fontDefault.generateFont(params)

    private val backgroundImage = Image(screen.game.gameAssets.timer_panel)
    private val timerLabel      = Label("01:00", Label.LabelStyle(font, Color.WHITE))

    var isPause = false

    override fun addActorsOnGroup() {
        addAndFillActor(backgroundImage)
        addTimerLbl()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addTimerLbl() {
        addActor(timerLabel)
        timerLabel.setBounds(99f, 21f, 123f, 46f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun startTimer(finishBlock: () -> Unit) {
        coroutine?.launch {
            var time    = 60f

            while (isActive && time > 0) {
                if (isPause.not()) {
                    delay(1000)
                    --time
                    val t = (time%60).toInt()
                    timerLabel.setText("00:${if (t<10) "0$t" else t}")
                }
            }
            if (time <= 0) {
                cancel()
                finishBlock()
            }
        }
    }


}