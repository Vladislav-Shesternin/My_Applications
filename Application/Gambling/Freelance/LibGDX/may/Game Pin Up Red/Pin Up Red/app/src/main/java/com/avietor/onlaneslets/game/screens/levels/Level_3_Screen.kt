package com.avietor.onlaneslets.game.screens.levels

import com.avietor.onlaneslets.game.box2d.bodies.BBigi
import com.avietor.onlaneslets.game.box2d.bodies.BEnemyTry
import com.avietor.onlaneslets.game.utils.advanced.AdvancedStage
import com.badlogic.gdx.math.Vector2
class Level_3_Screen: LevelScreen() {

    override val SCREEN by lazy { Level_3_Screen() }
    override val startPos = Vector2(390f, 436f)

    // Body
    private val bBigi     = BBigi(this)
    private val bEnemyTry = BEnemyTry(this)
    private val bBigi1     = BBigi(this)
    private val bEnemyTry1 = BEnemyTry(this)
    private val bBigi2     = BBigi(this)
    private val bEnemyTry2 = BEnemyTry(this)
    override fun AdvancedStage.addActorsOnStage() {
        createBigi()
        createTry()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createBigi() {
        bBigi.create(269f, 979f, 432f, 46f)
        bBigi1.create(0f, 679f, 432f, 46f)
        bBigi2.create(269f, 390f, 432f, 46f)
    }

    private fun createTry() {
        bEnemyTry.create(461f, 1025f, 93f, 71f)
        bEnemyTry1.create(170f, 725f, 93f, 71f)
        bEnemyTry2.create(551f, 436f, 93f, 71f)
    }

}