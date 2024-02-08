package com.vbbb.time.game.game.screens

import com.badlogic.gdx.math.Vector2
import com.vbbb.time.game.game.box2d.bodies.BBalka
import com.vbbb.time.game.game.utils.advanced.AdvancedStage

class Level_4_Screen: LevelScreen() {

    override val targetPos = Vector2(1075f, 88f)
    override val count = 1

    // Body
    private val bBalka = BBalka(this)
    private val bBalka2 = BBalka(this)



    override fun AdvancedStage.addActorsOnStage() {
        createB_Balka()
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Balka() {
        bBalka.create(557f, 270f, 41f, 300f)
        bBalka2.create(809f, 87f, 41f, 300f)
    }

}