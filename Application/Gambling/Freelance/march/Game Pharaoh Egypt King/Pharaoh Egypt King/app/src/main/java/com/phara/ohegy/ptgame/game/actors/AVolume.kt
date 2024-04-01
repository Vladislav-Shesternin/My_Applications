package com.phara.ohegy.ptgame.game.actors

import com.badlogic.gdx.scenes.scene2d.Touchable
import com.phara.ohegy.ptgame.game.actors.checkbox.ACheckBox
import com.phara.ohegy.ptgame.game.utils.advanced.AdvancedGroup
import com.phara.ohegy.ptgame.game.utils.advanced.AdvancedScreen

class AVolume(override val screen: AdvancedScreen): AdvancedGroup() {

    private val volumes = List(11) { ACheckBox(screen, ACheckBox.Static.Type.VALUE) }

    override fun addActorsOnGroup() {
        addVolumes()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addVolumes() {
        var nx = 0f

        volumes.onEach {
            touchable = Touchable.disabled
            addActor(it)
            it.setBounds(nx, 0f, 35f, 48f)
            nx += 38f
        }
    }

    fun update(num: Int) {
        volumes.onEach { it.uncheck() }
        repeat(num) { volumes[it].check() }
    }

}