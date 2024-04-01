package com.bottleber.lebeler.game.screens.bottler

import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.box2d.bodies.BBottle
import com.bottleber.lebeler.game.utils.advanced.AdvancedStage
import kotlinx.coroutines.flow.MutableStateFlow

class _1_BottlerScreen(_game: LibGDXGame): AbstractBottlerScreen(_game, 1) {

    private val bBottle = BBottle(this)

    override fun AdvancedStage.addActorsOnStage() {
        createB_Bottle()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Bottle() {
        bBottle.create(897f, 299f, 125f, 364f)
    }
}