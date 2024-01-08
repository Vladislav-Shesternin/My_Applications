package com.tigerfortune.jogo.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.tigerfortune.jogo.game.utils.advanced.AdvancedGroup
import com.tigerfortune.jogo.game.utils.advanced.AdvancedScreen
import com.tigerfortune.jogo.game.utils.font.FontParameter
import com.tigerfortune.jogo.game.utils.runGDX
import com.tigerfortune.jogo.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ATimerGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+":").setSize(29)
    private val font          = screen.fontGeneratorAngry.generateFont(fontParameter)

    private val timeLbl = Label("02:00", Label.LabelStyle(font, Color.WHITE))

    var isPause = false

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.gameAssets.time_panel))
        addTimeLabel()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addTimeLabel() {
        addActor(timeLbl)
        timeLbl.setBounds(78f, 18f, 102f, 29f)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun startTimer(timeOutBlock: () -> Unit) {
        coroutine?.launch {
            var timer  = 15
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