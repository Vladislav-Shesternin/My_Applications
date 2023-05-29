package com.avietor.onlaneslets.game.screens.levels

import com.avietor.onlaneslets.game.actors.RESULT
import com.avietor.onlaneslets.game.box2d.AbstractBodyGroup
import com.avietor.onlaneslets.game.box2d.BodyId
import com.avietor.onlaneslets.game.box2d.WorldUtil
import com.avietor.onlaneslets.game.box2d.bodies.BBall
import com.avietor.onlaneslets.game.box2d.bodies.BHor
import com.avietor.onlaneslets.game.box2d.bodies.BMini
import com.avietor.onlaneslets.game.box2d.bodies.BStar
import com.avietor.onlaneslets.game.box2d.bodiesGroup.BGBorders
import com.avietor.onlaneslets.game.manager.SpriteManager
import com.avietor.onlaneslets.game.screens.LevelsScreen
import com.avietor.onlaneslets.game.utils.Size
import com.avietor.onlaneslets.game.utils.actor.disable
import com.avietor.onlaneslets.game.utils.actor.setOnClickListener
import com.avietor.onlaneslets.game.utils.advanced.AdvancedBox2dScreen
import com.avietor.onlaneslets.game.utils.advanced.AdvancedScreen
import com.avietor.onlaneslets.game.utils.advanced.AdvancedStage
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align

abstract class LevelScreen : AdvancedBox2dScreen(WorldUtil()) {

    abstract val SCREEN: LevelScreen
    abstract val startPos: Vector2

    // Actors
    private val takeStarImage = Image(SpriteManager.GameRegion.TAKE_STAR.region)
    private val upImage       = Image(SpriteManager.GameRegion.KNOPKA.region)
    private val leftImage     = Image(SpriteManager.GameRegion.KNOPKA.region)
    private val rightImage    = Image(SpriteManager.GameRegion.KNOPKA.region)
    private val result        = RESULT()

    // Body
    private val bBall by lazy { BBall(this) }
    private val bDown by lazy { BHor(this) }
    private val bMini by lazy { BMini(this) }
    private val bStar by lazy { BStar(this) }

    // BodyGroup
    private val bgBorders by lazy { BGBorders(this) }



    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BARAKUDA.region)
        super.show()
        mainGroup.disable()
    }

    final override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createB_Down()
        createB_Mini()
        createB_Star()
        createB_Ball()

        addTakeStar()
        addActorsOnStage()

        addKnopki()
        addActor(result)
        result.setSize(WIDTH, HEIGHT)
    }

    open fun AdvancedStage.addActorsOnStage() {}

    override fun dispose() {
        super.dispose()
        listOf<AbstractBodyGroup>(bgBorders)
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addTakeStar() {
        addActor(takeStarImage)
        takeStarImage.setBounds(477f, 1313f, 205f, 71f)
    }
    private fun AdvancedStage.addKnopki() {
        addActors(leftImage, upImage, rightImage)
        leftImage.apply {
            setOrigin(Align.center)
            rotation = 90f
            setBounds(35f, 52f, 123f, 123f)
            setOnClickListener { left() }
        }
        upImage.apply {
            setBounds(289f, 52f, 123f, 123f)
            setOnClickListener { up() }
        }
        rightImage.apply {
            setOrigin(Align.center)
            rotation = -90f
            setBounds(544f, 52f, 123f, 123f)
            setOnClickListener { right() }
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Down() {
        bDown.id = BodyId.ENEMY
        bDown.fixtureDef.isSensor = true
        bDown.create(0f, -27f, 701f, 27f)
    }

    private fun createB_Mini() {
        bMini.create(0f, 1173f, 196f, 46f)
    }

    private fun createB_Star() {
        bStar.create(startPos, Size(71f, 71f))
    }

    private fun createB_Ball() {
        bBall.create(98f, 1219f, 94f, 94f)

        bBall.beginContactBlock = {
            when(it.id) {
                BodyId.ENEMY -> {
                    result.show(SCREEN, RESULT.Type.FAIL)
                    bBall.startDestroy()
                }
                BodyId.STAR -> {
                    result.show(SCREEN, RESULT.Type.WIN)
                    bBall.startDestroy()
                }
                else -> {}
            }
        }
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(Vector2(0f, 0f), Size(WIDTH, HEIGHT))
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun left() {
        bBall.body?.applyTorque(2000f, true)
    }

    private fun right() {
        bBall.body?.applyTorque(-2000f, true)
    }

    private fun up() {
        bBall.body?.apply { applyLinearImpulse(Vector2(0f, 100f), worldCenter, true) }
    }

}