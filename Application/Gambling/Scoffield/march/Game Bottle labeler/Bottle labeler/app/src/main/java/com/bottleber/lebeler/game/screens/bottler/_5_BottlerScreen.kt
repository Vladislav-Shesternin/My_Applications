package com.bottleber.lebeler.game.screens.bottler

import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.box2d.bodies.BBottle
import com.bottleber.lebeler.game.box2d.bodies.BSeparator
import com.bottleber.lebeler.game.utils.advanced.AdvancedStage

class _5_BottlerScreen(_game: LibGDXGame): AbstractBottlerScreen(_game, 6) {

    private val bBottleList1 = List(3) { BBottle(this) }
    private val bBottleList2 = List(2) { BBottle(this) }
    private val bBottleList3 = List(1) { BBottle(this) }
    private val bSeparator   = BSeparator(this)
    private val bSeparator2  = BSeparator(this)

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

        bBottleList3.onEach {
            it.create(936f, 738f, 87f, 252f)
        }
    }

    private fun createB_Separator() {
        bSeparator.createBox(816f, 517f, 327f, 10f)
        bSeparator2.createBox(880f, 728f, 199f, 10f)
    }
}