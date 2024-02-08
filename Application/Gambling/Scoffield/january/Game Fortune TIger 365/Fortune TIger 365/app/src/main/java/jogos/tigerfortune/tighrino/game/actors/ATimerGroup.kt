package jogos.tigerfortune.tighrino.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import jogos.tigerfortune.tighrino.game.screens.TLevelScreen
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedGroup
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedScreen
import jogos.tigerfortune.tighrino.game.utils.font.FontParameter
import jogos.tigerfortune.tighrino.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ATimerGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+":").setSize(50)
    private val font          = screen.fontGeneratorAngry.generateFont(fontParameter)

    private val timeLbl = Label("", Label.LabelStyle(font, Color.WHITE))

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
            var timer  = when(TLevelScreen.LEVEL) {
                TLevelScreen.Level.EASY -> 90
                TLevelScreen.Level.NORMAL -> 60
                TLevelScreen.Level.HARD -> 30
            }
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