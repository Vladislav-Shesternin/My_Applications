package com.bottleber.lebeler.game.screens.bottler

import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.box2d.bodies.BBottle
import com.bottleber.lebeler.game.box2d.bodies.BSeparator
import com.bottleber.lebeler.game.utils.advanced.AdvancedStage

class _3_BottlerScreen(_game: LibGDXGame): AbstractBottlerScreen(_game, 4) {

    private val bBottleList3 = List(3) { BBottle(this) }
    private val bBottleTop   = BBottle(this)
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
        bBottleList3.onEach {
            it.create(nx, 299f, 75f, 218f)
            nx += 77+75
        }

        bBottleTop.create(927f, 527f, 102f, 296f)
    }

    private fun createB_Separator() {
        bSeparator.createBox(816f, 517f, 327f, 10f)
    }
}