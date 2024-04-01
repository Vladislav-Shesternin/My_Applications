package com.egypt.tian.sto.game.game.actors.progress

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import com.egypt.tian.sto.game.game.actors.masks.Mask
import com.egypt.tian.sto.game.game.utils.advanced.AdvancedGroup
import com.egypt.tian.sto.game.game.utils.advanced.AdvancedScreen
import com.egypt.tian.sto.game.game.utils.font.FontParameter
import com.egypt.tian.sto.game.game.utils.runGDX

class ATimer(override val screen: AdvancedScreen): AdvancedGroup() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+":").setSize(48)
    private val font      = screen.fontGenerator_InknutAntiqua_Regular.generateFont(parameter)

    private val textLbl = Label("00:00", Label.LabelStyle(font, Color.WHITE))

    var isPause = false

    override fun addActorsOnGroup() {
        addAndFillActor(textLbl)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun startTimer(finishBlock: () -> Unit) {
        coroutine?.launch {
            var time    = 15f

            while (isActive && time > 0) {
                if (isPause.not()) {
                    delay(1000)
                    --time
                    val t = (time % 60).toInt()
                    textLbl.setText("00:${if (t<10) "0$t" else t}")
                }
            }
            if (time <= 0) {
                cancel()
                finishBlock()
            }
        }
    }


}