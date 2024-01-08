package com.tigerfortune.jogo.game.actors

import com.badlogic.gdx.scenes.scene2d.Touchable
import com.tigerfortune.jogo.game.actors.checkbox.ACheckBox
import com.tigerfortune.jogo.game.utils.actor.animHide
import com.tigerfortune.jogo.game.utils.actor.animShow
import com.tigerfortune.jogo.game.utils.advanced.AdvancedGroup
import com.tigerfortune.jogo.game.utils.advanced.AdvancedScreen

class AVolumeGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val volumes = List(7) { ACheckBox(screen, ACheckBox.Static.Type.VOLUME) }

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
            it.setBounds(nx, 0f, 44f, 72f)
            nx += 47f
        }
    }

    fun update(num: Int) {
        volumes.onEach { it.uncheck() }
        repeat(num) { volumes[it].check() }
    }

}