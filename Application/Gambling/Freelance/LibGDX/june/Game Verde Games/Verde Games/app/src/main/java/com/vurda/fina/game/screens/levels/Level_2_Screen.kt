package com.vurda.fina.game.screens.levels

import com.vurda.fina.game.box2d.bodies.BGold
import com.vurda.fina.game.box2d.bodies.BPlatforma
import com.vurda.fina.game.utils.advanced.AdvancedStage

class Level_2_Screen: LevelScreen() {

    override val countCUbok = 2

    // Body
    private val bPlatforma  = BPlatforma(this)
    private val bPlatforma1 = BPlatforma(this)
    private val bCubok      = BGold(this)
    private val bCubok1     = BGold(this)

    override fun AdvancedStage.addActorsOnStage() {
        createB_Platforma()
        createB_Gold()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Platforma() {
        bPlatforma.create(748f, 642f, 282f, 22f)
        bPlatforma1.create(1245f, 100f, 282f, 22f)
    }

    private fun createB_Gold() {
        bCubok.create(838f, 664f, 101f, 101f)
        bCubok1.create(1335f, 122f, 101f, 101f)
    }

}