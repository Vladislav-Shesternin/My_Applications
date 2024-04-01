package com.flamingo.nimbal.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.WeldJoint
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.flamingo.nimbal.game.LibGDXGame
import com.flamingo.nimbal.game.box2d.AbstractBody
import com.flamingo.nimbal.game.box2d.AbstractJoint
import com.flamingo.nimbal.game.box2d.BodyId
import com.flamingo.nimbal.game.box2d.WorldUtil
import com.flamingo.nimbal.game.box2d.bodies.BBird
import com.flamingo.nimbal.game.box2d.bodies.standart.BKinematic
import com.flamingo.nimbal.game.box2d.bodiesGroup.BGBeam
import com.flamingo.nimbal.game.box2d.bodiesGroup.BGBorders
import com.flamingo.nimbal.game.box2d.destroyAll
import com.flamingo.nimbal.game.utils.HEIGHT_UI
import com.flamingo.nimbal.game.utils.TIME_ANIM
import com.flamingo.nimbal.game.utils.WIDTH_UI
import com.flamingo.nimbal.game.utils.actor.animHide
import com.flamingo.nimbal.game.utils.actor.animShow
import com.flamingo.nimbal.game.utils.advanced.AdvancedBox2dScreen
import com.flamingo.nimbal.game.utils.advanced.AdvancedStage
import com.flamingo.nimbal.game.utils.advanced.isMoveBackckground
import com.flamingo.nimbal.game.utils.font.FontParameter
import com.flamingo.nimbal.game.utils.runGDX
import com.flamingo.nimbal.game.utils.toB2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class FlamingoGameScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    private val COUNT = 2

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(50)
    private val font50        = fontGenerator_MetamorphousRegular.generateFont(fontParameter)

    // Actor
    private val aCounterLbl = Label("Passed: 0", Label.LabelStyle(font50, Color.WHITE))

    // Body
    private val bBird          = BBird(this)
    private val bKinematicList = List(COUNT) { BKinematic(this) }

    // BodyGroup
    private val bgBorders  = BGBorders(this)
    private val bgBeamList = List(COUNT) { BGBeam(this) }

    // Joint
    private val jWeldList = List(COUNT) { AbstractJoint<WeldJoint, WeldJointDef>(this) }

    // Field
    private val atomicBooleanList = List(COUNT) { AtomicBoolean(true) }
    private var counter = 0

    override fun show() {
        isMoveBackckground = true
        super.show()
    }

    override fun dispose() {
        bgBorders.destroy()
        bgBeamList.destroyAll()
        super.dispose()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createB_Bird()

        createBG_Beam()
        createB_Kinematic()
        createJ_Weld()

        addCounterLbls()
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        game.soundUtil.apply { play(swipe, volumeLevel*0.3f) }

        bBird.body?.applyLinearImpulse(Vector2(0f, 50f), bBird.body?.position, true)
        return super.touchDown(screenX, screenY, pointer, button)
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addCounterLbls() {
        addActor(aCounterLbl)
        aCounterLbl.setBounds(37f, 24f, 283f, 63f)
    }

    // ---------------------------------------------------
    // create BodyGroup
    // ---------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(0f, 0f, WIDTH_UI, HEIGHT_UI)
    }

    private fun createBG_Beam() {
        bgBeamList.onEach { beam ->
            beam.create(1677f, -100f, 87f, 1653f)
        }
    }

    // ---------------------------------------------------
    // create Body
    // ---------------------------------------------------

    private fun createB_Bird() {
        bBird.create(179f, 450f, 180f, 180f)

        bBird.beginContactBlockArray.add(AbstractBody.ContactBlock {
            when(it.id) {
                BodyId.BORDERS -> {
                    game.soundUtil.apply { play(error, volumeLevel*0.3f) }

                    stageUI.root.animHide(TIME_ANIM) { game.navigationManager.navigate(FlamingoGameScreen::class.java.name) }
                }
                BodyId.Coyntry.WIN -> {
                    game.soundUtil.apply { play(bonus, volumeLevel*0.4f) }

                    counter++
                    aCounterLbl.setText("Passed: $counter")
                }
            }
        })
    }

    private fun createB_Kinematic() {
        val impulse = Vector2(20f, 20f)

        val tmpJoint        = AbstractJoint<WeldJoint, WeldJointDef>(this)
        val tmpWeldJointDef = WeldJointDef()

        bKinematicList.onEachIndexed { index, kinematic ->
            kinematic.create(1600f, 1000f, 50f, 50f)

            kinematic.renderBlockArray.add(AbstractBody.RenderBlock {
                if (kinematic.body!!.position.x < -3f) {
                    if (atomicBooleanList[index].getAndSet(false)) {
                        runGDX {
                            bgBeamList[index].actorList.onEach { it.animHide() }
                            kinematic.body!!.setTransform(Vector2(2300f, (755..1300).random().toFloat()).toB2, 0f)
                            bgBeamList[index].actorList.onEach { it.animShow(1f) }

                            tmpJoint.apply {
                                create(tmpWeldJointDef.apply {
                                    bodyA = bKinematicList[index].body
                                    bodyB = bgBeamList[index].bBeamBottom.body

                                    localAnchorA.set(Vector2(25f, 25f).toB2.sub((bodyA.userData as AbstractBody).center))
                                    localAnchorB.set(Vector2(40f, 1300f).toB2.sub((bodyB.userData as AbstractBody).center))
                                })
                            }

                            coroutine?.launch {
                                delay(300)
                                runGDX {
                                    tmpJoint.destroy()
                                    atomicBooleanList[index].set(true)

                                    bgBeamList[index].bBeamBottom.body?.apply {
                                        applyLinearImpulse(impulse, worldCenter, false)
                                    }
                                }
                            }
                        }
                    }
                }
            })
        }

        coroutine?.launch {
            bKinematicList.onEach { kinematic ->
                runGDX { kinematic.body?.setLinearVelocity(-3.2f, 0f) }
                delay(6000)
            }
        }
    }

    // ---------------------------------------------------
    // create Joint
    // ---------------------------------------------------

    private fun createJ_Weld() {
        jWeldList.onEachIndexed { index, joint ->
            joint.create(WeldJointDef().apply {
                bodyA = bKinematicList[index].body
                bodyB = bgBeamList[index].bBeamTop.body

                localAnchorA.set(Vector2(28f, 28f).toB2.sub((bodyA.userData as AbstractBody).center))
                localAnchorB.set(Vector2(183f, 352f).toB2.sub((bodyB.userData as AbstractBody).center))
            })
        }
    }

}