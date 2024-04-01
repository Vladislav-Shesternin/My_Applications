package com.bottleber.lebeler.game.screens.bottler

import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.box2d.bodies.BBottle
import com.bottleber.lebeler.game.box2d.bodies.BSeparator
import com.bottleber.lebeler.game.utils.advanced.AdvancedStage

class _8_BottlerScreen(_game: LibGDXGame): AbstractBottlerScreen(_game, 8) {

    private val bBottleList1 = List(3) { BBottle(this) }
    private val bBottleList2 = List(2) { BBottle(this) }
    private val bBottleList3 = List(2) { BBottle(this) }
    private val bBottleList4 = List(1) { BBottle(this) }
    private val bSeparator   = BSeparator(this)
    private val bSeparator2  = BSeparator(this)
    private val bSeparator3  = BSeparator(this)

    override fun AdvancedStage.addActorsOnStage() {
        createB_Bottle()
        createB_Separator()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Bottle() {
        var nx = 202f
        bBottleList1.onEach {
            it.create(nx, 299f, 84f, 246f)
            nx += 88+84
        }
        var nx2 = 289f
        bBottleList2.onEach {
            it.create(nx2, 555f, 84f, 246f)
            nx2 += 88+84
        }
        var nx3 = 356f
        bBottleList3.onEach {
            it.create(nx3, 811f, 39f, 113f)
            nx3 += 44+39
        }
        bBottleList4.onEach {
            it.create(401f, 934f, 30f, 89f)
        }
    }

    private fun createB_Separator() {
        bSeparator.createBox(232f, 545f, 369f, 10f)
        bSeparator2.createBox(318f, 801f, 199f, 10f)
        bSeparator3.createBox(367f, 924f, 98f, 10f)
    }
}