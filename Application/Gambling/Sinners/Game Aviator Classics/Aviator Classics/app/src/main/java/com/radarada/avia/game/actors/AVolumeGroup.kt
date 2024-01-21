package com.radarada.avia.game.actors

import com.radarada.avia.game.actors.checkbox.ACheckBox
import com.radarada.avia.game.utils.advanced.AdvancedGroup
import com.radarada.avia.game.utils.advanced.AdvancedScreen
import com.badlogic.gdx.scenes.scene2d.Touchable

class AVolumeGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val volumes = List(6) { ACheckBox(screen, ACheckBox.Static.Type.VOLUME) }

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
            nx += 40f
        }
    }

    fun update(num: Int) {
        volumes.onEach { it.uncheck() }
        repeat(num) { volumes[it].check() }
    }

}