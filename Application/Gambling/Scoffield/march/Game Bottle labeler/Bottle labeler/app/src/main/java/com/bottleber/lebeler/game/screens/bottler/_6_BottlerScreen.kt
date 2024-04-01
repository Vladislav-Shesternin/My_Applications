package com.bottleber.lebeler.game.screens.bottler

import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.box2d.bodies.BBottle
import com.bottleber.lebeler.game.box2d.bodies.BSeparator
import com.bottleber.lebeler.game.utils.advanced.AdvancedStage

class _6_BottlerScreen(_game: LibGDXGame): AbstractBottlerScreen(_game, 7) {

    private val bBottleList1 = List(6) { BBottle(this) }
    private val bBottleList2 = List(1) { BBottle(this) }
    private val bSeparator   = BSeparator(this)

    override fun AdvancedStage.addActorsOnStage() {
        createB_Bottle()
        createB_Separator()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Bottle() {
        var nx = 506f
        bBottleList1.onEach {
            it.create(nx, 299f, 75f, 218f)
            nx += 91+75
        }
        bBottleList2.onEach {
            it.create(890f, 526f, 136f, 394f)
        }
    }

    private fun createB_Separator() {
        bSeparator.createBox(698f, 517f, 520f, 10f)
    }
}