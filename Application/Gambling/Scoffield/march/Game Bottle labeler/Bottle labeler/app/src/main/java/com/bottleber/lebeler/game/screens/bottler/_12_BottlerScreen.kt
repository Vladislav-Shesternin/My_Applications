package com.bottleber.lebeler.game.screens.bottler

import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.box2d.bodies.BBottle
import com.bottleber.lebeler.game.box2d.bodies.BSeparator
import com.bottleber.lebeler.game.utils.advanced.AdvancedStage

class _12_BottlerScreen(_game: LibGDXGame): AbstractBottlerScreen(_game, 20) {

    private val bBottleList1 = List(5) { BBottle(this) }
    private val bBottleList2 = List(4) { BBottle(this) }
    private val bBottleList3 = List(5) { BBottle(this) }
    private val bBottleList4 = List(4) { BBottle(this) }
    private val bBottleList5 = List(2) { BBottle(this) }
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
        var nx = 593f
        bBottleList1.onEach {
            it.create(nx, 299f, 50f, 146f)
            nx += 120+50
        }
        var nx2 = 702f
        bBottleList2.onEach {
            it.create(nx2, 455f, 60f, 175f)
            nx2 += 90+60
        }
        var nx3 = 593f
        bBottleList3.onEach {
            it.create(nx3, 640f, 50f, 146f)
            nx3 += 120+50
        }
        var nx4 = 682f
        bBottleList4.onEach {
            it.create(nx4, 796f, 40f, 117f)
            nx4 += 130+40
        }
        var nx5 = 773f
        bBottleList5.onEach {
            it.create(nx5, 923f, 32f, 95f)
            nx5 += 307+32
        }
    }

    private fun createB_Separator() {
        bSeparator.createBox(608f, 445f, 700f, 10f)
        bSeparator2.createBox(608f, 630f, 700f, 10f)
        bSeparator3.createBox(608f, 786f, 700f, 10f)
        bSeparator4.createBox(608f, 913f, 700f, 10f)
    }
}