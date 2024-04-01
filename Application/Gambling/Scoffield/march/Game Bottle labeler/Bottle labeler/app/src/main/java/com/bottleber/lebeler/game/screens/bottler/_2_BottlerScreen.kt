package com.bottleber.lebeler.game.screens.bottler

import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.box2d.bodies.BBottle
import com.bottleber.lebeler.game.utils.advanced.AdvancedStage
import kotlinx.coroutines.flow.MutableStateFlow

class _2_BottlerScreen(_game: LibGDXGame): AbstractBottlerScreen(_game, 3) {

    private val bBottle = List(3) { BBottle(this) }

    override fun AdvancedStage.addActorsOnStage() {
        createB_Bottle()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Bottle() {
        var nx = 747f
        bBottle.onEach {
            it.create(nx, 299f, 125f, 364f)
            nx += 35+125
        }
    }
}