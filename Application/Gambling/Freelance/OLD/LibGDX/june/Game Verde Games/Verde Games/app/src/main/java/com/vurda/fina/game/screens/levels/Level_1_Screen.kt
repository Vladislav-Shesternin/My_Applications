package com.vurda.fina.game.screens.levels

import com.vurda.fina.game.box2d.bodies.BGold
import com.vurda.fina.game.box2d.bodies.BPlatforma
import com.vurda.fina.game.utils.advanced.AdvancedStage

class Level_1_Screen: LevelScreen() {

    override val countCUbok = 1

    // Body
    private val bPlatforma = BPlatforma(this)
    private val bCubok     = BGold(this)

    override fun AdvancedStage.addActorsOnStage() {
        createB_Platforma()
        createB_Gold()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Platforma() {
        bPlatforma.create(1089f, 299f, 282f, 22f)
    }

    private fun createB_Gold() {
        bCubok.create(1179f, 321f, 101f, 101f)
    }

}