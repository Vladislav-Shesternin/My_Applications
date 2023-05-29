package com.avietor.onlaneslets.game.screens.levels

import com.avietor.onlaneslets.game.box2d.bodies.BBigi
import com.avietor.onlaneslets.game.box2d.bodies.BEnemyRotte
import com.avietor.onlaneslets.game.utils.advanced.AdvancedStage
import com.badlogic.gdx.math.Vector2
class Level_5_Screen: LevelScreen() {

    override val SCREEN by lazy { Level_5_Screen() }
    override val startPos = Vector2(425f, 371f)

    // Body
    private val bMini     = BBigi(this)
    private val bBigi     = BBigi(this)
    private val bEnemyRott = BEnemyRotte(this)
    private val bEnemyRott1 = BEnemyRotte(this)

    override fun AdvancedStage.addActorsOnStage() {
        createBigi()
        createRott()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createBigi() {
        bMini.create(363f, 325f, 196f, 46f)
        bBigi.create(269f, 851f, 432f, 46f)
    }

    private fun createRott() {
        bEnemyRott.fixtureDef.density = 600f
        bEnemyRott1.fixtureDef.density = 600f
        bEnemyRott.create(406f, 910f, 110f, 110f)
        bEnemyRott1.create(61f, 600f, 110f, 110f)
    }

}