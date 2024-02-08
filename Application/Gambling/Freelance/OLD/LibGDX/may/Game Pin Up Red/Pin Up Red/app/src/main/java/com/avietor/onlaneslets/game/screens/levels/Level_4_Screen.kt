package com.avietor.onlaneslets.game.screens.levels

import com.avietor.onlaneslets.game.box2d.bodies.BBigi
import com.avietor.onlaneslets.game.box2d.bodies.BEnemyRotte
import com.avietor.onlaneslets.game.utils.advanced.AdvancedStage
import com.badlogic.gdx.math.Vector2
class Level_4_Screen: LevelScreen() {

    override val SCREEN by lazy { Level_4_Screen() }
    override val startPos = Vector2(390f, 436f)

    // Body
    private val bBigi     = BBigi(this)
    private val bEnemyRott = BEnemyRotte(this)

    override fun AdvancedStage.addActorsOnStage() {
        createBigi()
        createRott()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createBigi() {
        bBigi.create(269f, 390f, 432f, 46f)
    }

    private fun createRott() {
        bEnemyRott.create(252f, 702f, 280f, 280f)
    }

}