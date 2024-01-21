package jogo.fortunetiger.tighrino.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedGroup
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedScreen
import jogo.fortunetiger.tighrino.game.utils.font.FontParameter
import jogo.fortunetiger.tighrino.game.utils.runGDX
import jogo.fortunetiger.tighrino.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ATimerGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+":").setSize(50)
    private val font          = screen.fontGeneratorAngry.generateFont(fontParameter)

    private val timeLbl = Label("1:30", Label.LabelStyle(font, Color.WHITE))

    var isPause = false

    val getTime: String get() = timeLbl.text.toString()

    override fun addActorsOnGroup() {
        addAndFillActor(timeLbl)
        timeLbl.setAlignment(Align.center)
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

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
                        timeLbl.setText("$minute:${if (second < 10) "0$second" else second}")
                    }
                }
            }

            if (timer <= 0) runGDX { timeOutBlock() }
        }
    }

}