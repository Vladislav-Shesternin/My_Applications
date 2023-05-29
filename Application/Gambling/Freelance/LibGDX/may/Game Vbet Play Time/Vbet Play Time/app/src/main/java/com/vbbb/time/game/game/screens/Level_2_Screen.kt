package com.vbbb.time.game.game.screens

import com.badlogic.gdx.math.Vector2
import com.vbbb.time.game.game.box2d.bodies.BBalka
import com.vbbb.time.game.game.utils.advanced.AdvancedStage

class Level_2_Screen: LevelScreen() {

    override val targetPos = Vector2(1025f, 228f)
    override val count = 2

    // Body
    private val bBalka = BBalka(this)



    override fun AdvancedStage.addActorsOnStage() {
        createB_Balka()
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Balka() {
        bBalka.create(720f, 87f, 41f, 300f)
    }

}