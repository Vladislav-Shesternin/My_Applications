package com.bottleber.lebeler.game.screens.bottler

import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.box2d.bodies.BBottle
import com.bottleber.lebeler.game.box2d.bodies.BSeparator
import com.bottleber.lebeler.game.utils.advanced.AdvancedStage

class _9_BottlerScreen(_game: LibGDXGame): AbstractBottlerScreen(_game, 9) {

    private val bBottleList1 = List(3) { BBottle(this) }
    private val bBottleList2 = List(2) { BBottle(this) }
    private val bBottleList3 = List(2) { BBottle(this) }
    private val bBottleList4 = List(2) { BBottle(this) }
    private val bSeparator   = BSeparator(this)
    private val bSeparator2  = BSeparator(this)
    private val bSeparator3  = BSeparator(this)
    private val bSeparator4  = BSeparator(this)

    override fun AdvancedStage.addActorsOnStage() {
        createB_Bottle()
        createB_Separator()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Bottle() {
        var nx = 787f
        bBottleList1.onEach {
            it.create(nx, 299f, 68f, 197f)
            nx += 71+68
        }
        var nx2 = 834f
        bBottleList2.onEach {
            it.create(nx2, 506f, 84f, 246f)
            nx2 += 88+84
        }
        var nx3 = 862f
        bBottleList3.onEach {
            it.create(nx3, 762f, 30f, 89f)
            nx3 += 141+30
        }
        var nx4 = 898f
        bBottleList4.onEach {
            it.create(nx4, 861f, 39f, 113f)
            nx4 += 44+39
        }
    }

    private fun createB_Separator() {
        bSeparator.createBox(777f, 496f, 369f, 10f)
        bSeparator2.createBox(828f, 752f, 98f, 10f)
        bSeparator3.createBox(1000f, 752f, 98f, 10f)
        bSeparator4.createBox(860f, 851f, 199f, 10f)
    }
}