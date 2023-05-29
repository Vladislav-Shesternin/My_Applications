package com.avietor.onlaneslets.game.screens.levels

import com.avietor.onlaneslets.game.box2d.bodies.BBigi
import com.avietor.onlaneslets.game.box2d.bodies.BEnemyTry
import com.avietor.onlaneslets.game.utils.advanced.AdvancedStage
import com.badlogic.gdx.math.Vector2
class Level_1_Screen: LevelScreen() {

    override val SCREEN by lazy { Level_1_Screen() }
    override val startPos = Vector2(592f, 419f)

    // Body
    private val bBigi     = BBigi(this)
    private val bEnemyTry = BEnemyTry(this)

    override fun AdvancedStage.addActorsOnStage() {
        createBigi()
        createTry()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createBigi() {
        bBigi.create(269f, 364f, 432f, 46f)
    }

    private fun createTry() {
        bEnemyTry.create(461f, 410f, 93f, 71f)
    }

}