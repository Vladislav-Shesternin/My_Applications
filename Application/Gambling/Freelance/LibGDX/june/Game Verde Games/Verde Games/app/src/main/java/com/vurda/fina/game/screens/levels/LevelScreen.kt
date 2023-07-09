package com.vurda.fina.game.screens.levels

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.vurda.fina.game.box2d.*
import com.vurda.fina.game.box2d.bodies.BBall
import com.vurda.fina.game.box2d.bodies.BSide
import com.vurda.fina.game.box2d.bodiesGroup.BGBorders
import com.vurda.fina.game.manager.NavigationManager
import com.vurda.fina.game.manager.SpriteManager
import com.vurda.fina.game.utils.Size
import com.vurda.fina.game.utils.actor.disable
import com.vurda.fina.game.utils.advanced.AdvancedBox2dScreen
import com.vurda.fina.game.utils.advanced.AdvancedStage
import com.vurda.fina.game.utils.runGDX
import com.vurda.fina.game.utils.vector2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class LevelScreen : AdvancedBox2dScreen(WorldUtil()) {

    abstract val countCUbok: Int

    // Actors
    private val sideImage = Image(SpriteManager.GameRegion.SIDE.region)
    private val ballList  = List(3) { Image(SpriteManager.GameRegion.BALL.region) }
    private val win       = Image(SpriteManager.GameRegion.YOU_ARE_WIN.region)
    private val cuboks    by lazy { List(countCUbok) { Image(SpriteManager.GameRegion.CUB_NE.region) } }

    // Body
    private val bBall by lazy { BBall(this) }
    private val bSide by lazy { BSide(this) }

    // Joint
    private val jMouse by lazy { AbstractJoint<MouseJoint>(this) }

    // BodyGroup
    private val bgBorders by lazy { BGBorders(this) }

    // Fields
    private var isAgreeBall = true
    private var ballCount   = 3
    private var cubok       = 0


    open fun AdvancedStage.addActorsOnStage() {}
    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BARAGRANDOM.region)
        super.show()
    }
    final override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()

        addBall()
        addSide()
        addCubok()

        createB_Side()
        createB_Ball()

        addActorsOnStage()

        addListener(getInputListener())
    }

    override fun dispose() {
        super.dispose()
        listOf<AbstractBodyGroup>(bgBorders)
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addBall() {
        var ny = 564f
        ballList.onEachIndexed { index, image ->
            addActor(image)
            image.setBounds(20f, ny, 62f, 62f)
            ny += 62 + 15
        }
    }

    private fun AdvancedStage.addSide() {
        addActor(sideImage)
        sideImage.setBounds(0f, 0f, 369f, 356f)
    }

    private fun AdvancedStage.addCubok() {
        addActor(win)
        win.setBounds(523f, 189f, 509f, 422f)
        win.apply {
            disable()
            addAction(Actions.alpha(0f))
        }

        var nx = 1438f
        cuboks.onEach {
            addActor(it)
            it.setBounds(nx, 668f, 89f, 89f)
            nx -= 23f + 89f
        }
    }


    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------
    private fun createB_Side() {
        bSide.create(380f, 0f, 40f, 800f)
    }
    private fun createB_Ball() {
        bBall.bodyDef.gravityScale = 0f
        bBall.create(93f, 120f, 116f, 116f)

        bBall.beginContactBlock = {
            when(it.id) {
                BodyId.SIDE -> {
                    it.id = BodyId.NONE
                    isAgreeBall = false
                    jMouse.startDestroy()

                    ballCount--
                    ballList[2-ballCount].drawable = TextureRegionDrawable(SpriteManager.GameRegion.BALL_NOT.region)
                    if (ballCount > 0) {
                        coroutine.launch {
                            delay(4_000)
                            runGDX {
                                bBall.body?.apply {
                                    linearVelocity.set(0f, 0f)
                                    gravityScale = 0f
                                    isAwake      = false
                                    setTransform(sizeConverterUIToBox.getSize(151f, 167f).vector2, 0f)
                                }
                                it.id = BodyId.SIDE
                                isAgreeBall = true
                            }
                        }
                    } else {
                        coroutine.launch {
                            delay(4_000)

                            if (cubok >= countCUbok) {
                                win.addAction(Actions.sequence(
                                    Actions.fadeIn(1f),
                                    Actions.run { NavigationManager.back() }
                                ))
                            } else NavigationManager.back()
                        }
                    }

                }
                BodyId.GOLD -> {
                    it.id = BodyId.NONE
                    it.body?.applyLinearImpulse(Vector2(20f, 30f), it.center, true)
                    cubok++

                    if ((cubok - 1) <= cuboks.lastIndex) {
                        cuboks[cubok - 1].drawable = TextureRegionDrawable(SpriteManager.GameRegion.CUBOK_E.region)
                    }
                }
                else -> {}
            }
        }
    }

//    private fun createB_Ball() {
//        bBall.create(98f, 1219f, 94f, 94f)
//
//        bBall.beginContactBlock = {
//            when(it.id) {
//                BodyId.ENEMY -> {
//                    result.show(SCREEN, RESULT.Type.FAIL)
//                    bBall.startDestroy()
//                }
//                BodyId.STAR -> {
//                    result.show(SCREEN, RESULT.Type.WIN)
//                    bBall.startDestroy()
//                }
//                else -> {}
//            }
//        }
//    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(Vector2(0f, 0f), Size(WIDTH, HEIGHT))
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun getInputListener() = object : InputListener() {

        var hitAbstractBody: AbstractBody? = null
        val touchPointInBox = Vector2()
        val callback   = QueryCallback { fixture ->
            if (isAgreeBall) {
                if ((fixture.body.userData as AbstractBody).id != BodyId.BALL) return@QueryCallback true

                if (fixture.testPoint(touchPointInBox)) {
                    hitAbstractBody = fixture.body?.userData as AbstractBody
                    return@QueryCallback false
                }
            }
            return@QueryCallback true
        }

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchPointInBox.set(sizeConverterUIToBox.getSize(Vector2(x, y)))
            hitAbstractBody = null
            worldUtil.world.QueryAABB(callback,
                touchPointInBox.x - 0.01f,
                touchPointInBox.y - 0.01f,
                touchPointInBox.x + 0.01f,
                touchPointInBox.y + 0.01f)

            hitAbstractBody?.let {

                runGDX {
                    bBall.body?.gravityScale = 1f
                }

                jMouse.create(MouseJointDef().apply {
                    bodyA = bgBorders.bDown.body
                    bodyB = it.body
                    collideConnected = true

                    target.set(touchPointInBox)
                    maxForce = 1000 * bodyB.mass
                })
            }
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            jMouse.joint?.target = sizeConverterUIToBox.getSize(Vector2(x, y))
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            jMouse.startDestroy()
        }
    }

}