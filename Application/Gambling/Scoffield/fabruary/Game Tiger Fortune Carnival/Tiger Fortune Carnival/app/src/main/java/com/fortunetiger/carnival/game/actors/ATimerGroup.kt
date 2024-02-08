package com.fortunetiger.carnival.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.fortunetiger.carnival.game.utils.advanced.AdvancedGroup
import com.fortunetiger.carnival.game.utils.advanced.AdvancedScreen
import com.fortunetiger.carnival.game.utils.font.FontParameter
import com.fortunetiger.carnival.game.utils.runGDX
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ATimerGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+":").setSize(50)
    private val font          = screen.fontGeneratorBlackHanSans.generateFont(fontParameter)

    private val timeLbl = Label("", Label.LabelStyle(font, Color.WHITE))

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
            var timer  = 20
            //var minute: Int
            var second: Int

            while (isActive && timer > 0) {
                delay(1000)
                if (isPause.not()) {
                    timer -= 1
                    //minute = timer / 60
                    second = timer % 60

                    runGDX {
                        timeLbl.setText("$second")
                    }
                }
            }

            if (timer <= 0) runGDX { timeOutBlock() }
        }
    }

}