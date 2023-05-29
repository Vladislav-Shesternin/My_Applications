package com.avietor.onlaneslets.game.screens.levels

import com.avietor.onlaneslets.game.box2d.bodies.BBigi
import com.avietor.onlaneslets.game.box2d.bodies.BEnemyTry
import com.avietor.onlaneslets.game.utils.advanced.AdvancedStage
import com.badlogic.gdx.math.Vector2
class Level_2_Screen: LevelScreen() {

    override val SCREEN by lazy { Level_2_Screen() }
    override val startPos = Vector2(38f, 545f)

    // Body
    private val bBigi     = BBigi(this)
    private val bEnemyTry = BEnemyTry(this)
    private val bBigi1     = BBigi(this)
    private val bEnemyTry1 = BEnemyTry(this)
    override fun AdvancedStage.addActorsOnStage() {
        createBigi()
        createTry()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createBigi() {
        bBigi.create(269f, 887f, 432f, 46f)
        bBigi1.create(0f, 490f, 432f, 46f)
    }

    private fun createTry() {
        bEnemyTry.create(461f, 933f, 93f, 71f)
        bEnemyTry1.create(170f, 536f, 93f, 71f)
    }

}