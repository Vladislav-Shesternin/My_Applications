package aviator.original.win.game.actors

import aviator.original.win.game.utils.advanced.AdvancedGroup
import aviator.original.win.game.utils.advanced.AdvancedScreen
import aviator.original.win.game.utils.font.FontParameter
import aviator.original.win.game.utils.runGDX
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ATimerGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+":").setSize(21)
    private val font          = screen.fontGenerator_Bold.generateFont(fontParameter)

    private val timeLbl = Label("01:30", Label.LabelStyle(font, Color.WHITE))

    var isPause = false

    override fun addActorsOnGroup() {
        addAndFillActor(timeLbl)
        timeLbl.setAlignment(Align.center)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun startTimer(timeOutBlock: () -> Unit) {
        coroutine?.launch {
            var timer  = 90
            var minute: Int
            var second: Int

            while (isActive && timer > 0) {
                delay(1000)
                if (isPause.not()) {
                    timer -= 1
                    minute = timer / 60
                    second = timer % 60

                    runGDX {
                        timeLbl.setText("0$minute:${if (second < 10) "0$second" else second}")
                    }
                }
            }

            if (timer <= 0) runGDX { timeOutBlock() }
        }
    }

}