package com.bottleber.lebeler.game.screens.bottler

import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.box2d.bodies.BBottle
import com.bottleber.lebeler.game.box2d.bodies.BSeparator
import com.bottleber.lebeler.game.utils.advanced.AdvancedStage

class _4_BottlerScreen(_game: LibGDXGame): AbstractBottlerScreen(_game, 5) {

    private val bBottleList1 = List(3) { BBottle(this) }
    private val bBottleList2 = List(2) { BBottle(this) }
    private val bSeparator   = BSeparator(this)

    override fun AdvancedStage.addActorsOnStage() {
        createB_Bottle()
        createB_Separator()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Bottle() {
        var nx = 788f
        bBottleList1.onEach {
            it.create(nx, 299f, 75f, 218f)
            nx += 77+75
        }
        var nx2 = 867f
        bBottleList2.onEach {
            it.create(nx2, 527f, 69f, 201f)
            nx2 += 85+69
        }
    }

    private fun createB_Separator() {
        bSeparator.createBox(816f, 517f, 327f, 10f)
    }
}