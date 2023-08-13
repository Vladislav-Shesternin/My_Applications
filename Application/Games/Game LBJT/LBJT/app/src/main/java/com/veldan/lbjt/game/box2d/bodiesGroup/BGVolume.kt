package com.veldan.lbjt.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.MotorJoint
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.lbjt.game.actors.label.AutoLanguageSpinningLabel
import com.veldan.lbjt.game.actors.volume.AVolume
import com.veldan.lbjt.game.box2d.AbstractBodyGroup
import com.veldan.lbjt.game.box2d.AbstractJoint
import com.veldan.lbjt.game.box2d.BodyId
import com.veldan.lbjt.game.box2d.BodyId.Settings.LANGUAGE
import com.veldan.lbjt.game.box2d.BodyId.Settings.START
import com.veldan.lbjt.game.box2d.BodyId.Settings.VOLUME
import com.veldan.lbjt.game.box2d.bodies.BStatic
import com.veldan.lbjt.game.box2d.bodies.BVolume
import com.veldan.lbjt.game.box2d.bodies.BVolumeRod
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.JOINT_WIDTH
import com.veldan.lbjt.game.utils.actor.setBounds
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedStage
import com.veldan.lbjt.game.utils.runGDX
import com.veldan.lbjt.game.utils.toB2
import com.veldan.lbjt.game.utils.toUI
import com.veldan.lbjt.util.Once
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class BGVolume(
    override val screenBox2d: AdvancedBox2dScreen,
    private val nameResId: Int,
    private val startVolumePercent: Int,
): AbstractBodyGroup() {

    companion object {
        private const val MIN_LOUDER         = 39.80f
        private const val MAX_LOUDER         = 26.30f
        private const val ONE_PERCENT_LOUDER = (MIN_LOUDER - MAX_LOUDER) / 100f
    }

    override val standartW = 314f

    private val fontTTFUtil by lazy { screenBox2d.game.fontTTFUtil }

    // Actor
    private val aNameLbl by lazy { AutoLanguageSpinningLabel(screenBox2d, nameResId, Label.LabelStyle(fontTTFUtil.fontInterExtraBold.font_50.font, GameColor.textGreen)) }

    // Body
    private val bVolumeQuiet     = BVolume(screenBox2d, AVolume.Type.QUIET)
    private val bVolumeLouder    = BVolume(screenBox2d, AVolume.Type.LOUDER)
    private val bVolumeRodQuiet  = BVolumeRod(screenBox2d, BVolumeRod.Type.QUIET)
    private val bVolumeRodLouder = BVolumeRod(screenBox2d, BVolumeRod.Type.LOUDER)
    private val bStaticStart     = BStatic(screenBox2d)

    // Joint
    private val jPulley     = AbstractJoint<PulleyJoint, PulleyJointDef>(screenBox2d)
    private val jMotorStart = AbstractJoint<MotorJoint, MotorJointDef>(screenBox2d)

    // Field
    private val drawer = screenBox2d.drawerUtil.drawer

    private val percentLouderFlow by lazy { MutableStateFlow(startVolumePercent) }
    val percentVolumeFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)


    override fun requestToCreate(position: Vector2, size: Vector2, block: () -> Unit) {
        super.requestToCreate(position, size, block)

        screenBox2d.stageUI.apply {
            addNameLbl()
        }

        initB_Volume()
        initB_VolumeRod()
        initB_StaticStart()

        createB_Volume()
        createB_VolumeRod()
        createB_StaticStart()

        finishCreate {
            block()
            createJ_Pulley()
            asyncCollectPercentLouderFlow()
        }

    }

    // ---------------------------------------------------
    // Init
    // ---------------------------------------------------

    private fun initB_Volume() {
        arrayOf(bVolumeQuiet, bVolumeLouder).onEach { it.apply {
            id  = VOLUME
            collisionList.add(VOLUME)
        } }
    }

    private fun initB_VolumeRod() {
        arrayOf(bVolumeRodQuiet, bVolumeRodLouder).onEach { it.apply {
            id  = VOLUME
            collisionList.addAll(arrayOf(VOLUME, START, LANGUAGE, BodyId.BORDERS))

            bodyDef.linearDamping  = 1.3f
            bodyDef.angularDamping = 10f
        } }
    }

    private fun initB_StaticStart() {
        bStaticStart.apply {
            id  = START
            collisionList.add(VOLUME)
            fixtureDef.isSensor = true
        }
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addNameLbl() {
        addActor(aNameLbl)
        standardizer.scope { aNameLbl.setBounds(position.cpy().add(Vector2(0f, 565f).toStandart), Vector2(314f, 65f).toStandart) }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Volume() {
        createBody(bVolumeQuiet, 0f, 440f, 149f, 102f)
        createBody(bVolumeLouder, 165f, 440f, 149f, 102f)
    }

    private fun createB_VolumeRod() {
        createBody(bVolumeRodQuiet, 51f, 390f, 47f, 47f)
        createBody(bVolumeRodLouder, 216f, 390f, 47f, 47f)
    }

    private fun createB_StaticStart() {
        createBody(bStaticStart, 238f, 353f, 3f, 3f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Pulley() {
        createJoint(jPulley, PulleyJointDef().apply {
            bodyA = bVolumeRodQuiet.body
            bodyB = bVolumeRodLouder.body

            standardizer.scope {
                groundAnchorA.set(position.cpy().add(Vector2(74f, 440f).toStandart).toB2)
                groundAnchorB.set(position.cpy().add(Vector2(239f, 440f).toStandart).toB2)
                localAnchorA.set(Vector2(23f, 23f).subCenter(bodyA))
                localAnchorB.set(Vector2(23f, 23f).subCenter(bodyB))
                lengthA = 209f.toStandart.toB2
                lengthB = 209f.toStandart.toB2
            }

            bVolumeRodQuiet.actor?.blockPreDraw = { alpha -> drawer.line(groundAnchorA.cpy().toUI, bodyA.position.add(localAnchorA).toUI, GameColor.joint.apply { a = alpha }, JOINT_WIDTH) }
            bVolumeRodLouder.actor?.blockPreDraw = { alpha -> drawer.line(groundAnchorB.cpy().toUI, bodyB.position.add(localAnchorB).toUI, GameColor.joint.apply { a = alpha }, JOINT_WIDTH) }

        })
    }

    private fun createJ_MotorStart() {
        createJoint(jMotorStart, MotorJointDef().apply {
            bodyA = bStaticStart.body
            bodyB = bVolumeRodLouder.body
            collideConnected = true

            maxForce  = 500 * bodyB.mass
            maxTorque = 1000f
            correctionFactor = 0.1f
        })
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun asyncCollectPercentLouderFlow() {
        coroutine?.launch(Dispatchers.Default) {
            percentLouderFlow.collectIndexed { index, percent ->
                if (index == 0) {
                    runGDX { bStaticStart.body?.let { it.setTransform(it.position.x, MIN_LOUDER-(ONE_PERCENT_LOUDER*percent), 0f) } }
                    launch { createJ_MotorStart() }

                    runGDX { bVolumeRodLouder.apply {
                        val onceDestroy = Once()
                        beginContactBlock = { if (it.id == START) onceDestroy.once { coroutine?.launch {
                            delay(700)
                            runGDX { bStaticStart.requestToDestroy() }
                        } } }
                        renderBlock = { (body?.position?.y ?: 0f).also { _y ->
                            percentLouderFlow.value = when {
                                _y >= MIN_LOUDER -> 0
                                _y <= MAX_LOUDER -> 100
                                else -> ((MIN_LOUDER - _y) / ONE_PERCENT_LOUDER).roundToInt()
                            }
                        } }
                    } }
                }
                runGDX {
                    bVolumeLouder.getActor()?.updatePercent(percent)
                    bVolumeQuiet.getActor()?.updatePercent(100 - percent)

                    if (bStaticStart.body == null) percentVolumeFlow.tryEmit(percent)
                }
            }
        }
    }

}