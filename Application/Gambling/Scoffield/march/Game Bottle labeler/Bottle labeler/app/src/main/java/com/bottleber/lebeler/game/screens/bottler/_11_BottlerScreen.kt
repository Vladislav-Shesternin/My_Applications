package com.bottleber.lebeler.game.screens.bottler

import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.box2d.bodies.BBottle
import com.bottleber.lebeler.game.box2d.bodies.BSeparator
import com.bottleber.lebeler.game.utils.advanced.AdvancedStage

class _11_BottlerScreen(_game: LibGDXGame): AbstractBottlerScreen(_game, 11) {

    private val bBottleList1 = List(4) { BBottle(this) }
    private val bBottleList2 = List(2) { BBottle(this) }
    private val bBottleList3 = List(4) { BBottle(this) }
    private val bBottleList4 = List(1) { BBottle(this) }
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
        var nx = 617f
        bBottleList1.onEach {
            it.create(nx, 299f, 87f, 252f)
            nx += 100+87
        }
        var nx2 = 736f
        bBottleList2.onEach {
            it.create(nx2, 560f, 38f, 110f)
            nx2 += 338+38
        }
        var nx3 = 808f
        bBottleList3.onEach {
            it.create(nx3, 680f, 30f, 87f)
            nx3 += 50+30
        }
        bBottleList4.onEach {
            it.create(906f, 778f, 72f, 210f)
        }
    }

    private fun createB_Separator() {
        bSeparator.createBox(631f, 551f, 249f, 10f)
        bSeparator2.createBox(1007f, 551f, 249f, 10f)
        bSeparator3.createBox(749f, 671f, 388f, 10f)
        bSeparator4.createBox(817f, 768f, 250f, 10f)
    }
}