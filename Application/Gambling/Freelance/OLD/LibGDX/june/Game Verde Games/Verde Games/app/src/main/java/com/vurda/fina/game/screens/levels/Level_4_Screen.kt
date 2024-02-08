package com.vurda.fina.game.screens.levels

import com.vurda.fina.game.box2d.bodies.BGold
import com.vurda.fina.game.box2d.bodies.BPlatforma
import com.vurda.fina.game.utils.advanced.AdvancedStage

class Level_4_Screen: LevelScreen() {

    override val countCUbok = 3

    // Body
    private val bPlatforma  = BPlatforma(this)
    private val bPlatforma1 = BPlatforma(this)
    private val bPlatforma2 = BPlatforma(this)
    private val bCubok      = BGold(this)
    private val bCubok1     = BGold(this)
    private val bCubok2     = BGold(this)

    override fun AdvancedStage.addActorsOnStage() {
        createB_Platforma()
        createB_Gold()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Platforma() {
        bPlatforma.create(598f, 311f, 282f, 22f)
        bPlatforma1.create(880f, 472f, 282f, 22f)
        bPlatforma2.create(1162f, 311f, 282f, 22f)
    }

    private fun createB_Gold() {
        bCubok.create(688f, 333f, 101f, 101f)
        bCubok1.create(970f, 494f, 101f, 101f)
        bCubok2.create(1252f, 333f, 101f, 101f)
    }

}