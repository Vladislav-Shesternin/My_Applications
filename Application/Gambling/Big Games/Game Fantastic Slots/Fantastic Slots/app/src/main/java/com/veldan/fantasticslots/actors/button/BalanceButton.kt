package com.veldan.fantasticslots.actors.button

import com.badlogic.gdx.audio.Sound
import com.veldan.fantasticslots.actors.button.ButtonClickable
import com.veldan.fantasticslots.actors.label.LabelStyleUtil
import com.veldan.fantasticslots.actors.label.RollingLabel
import com.veldan.fantasticslots.advanced.AdvancedGroup
import com.veldan.fantasticslots.assets.util.SoundUtil
import com.veldan.fantasticslots.utils.disable
import com.veldan.fantasticslots.utils.log
import com.veldan.fantasticslots.utils.transformToBalanceFormat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BalanceButton : AdvancedGroup() {

    private val button = ButtonClickable(ButtonClickable.Style.balance)
    private val label  = RollingLabel("", LabelStyleUtil.robotoMono60)



    override fun sizeChanged() {
        if (width > 0 && height > 0) addActorsOnGroup()
    }



    private fun addActorsOnGroup() {
        addAndFillActors(button, label)
        label.disable()
    }



    fun setBalance(balance: Long) {
        label.setText(balance.transformToBalanceFormat())
    }

    fun setOnClickListener(sound: Sound? = SoundUtil.CLICK_BALANCE, block: () -> Unit) {
        button.setOnClickListener(sound, block)
    }

}