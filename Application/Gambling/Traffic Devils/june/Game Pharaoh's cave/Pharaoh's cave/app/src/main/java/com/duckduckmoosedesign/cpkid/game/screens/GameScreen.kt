package com.duckduckmoosedesign.cpkid.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.MotorJoint
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.duckduckmoosedesign.cpkid.game.LibGDXGame
import com.duckduckmoosedesign.cpkid.game.actors.AButton
import com.duckduckmoosedesign.cpkid.game.box2d.AbstractBody
import com.duckduckmoosedesign.cpkid.game.box2d.AbstractJoint
import com.duckduckmoosedesign.cpkid.game.box2d.BodyId
import com.duckduckmoosedesign.cpkid.game.box2d.bodies.*
import com.duckduckmoosedesign.cpkid.game.utils.*
import com.duckduckmoosedesign.cpkid.game.utils.actor.animHide
import com.duckduckmoosedesign.cpkid.game.utils.actor.animShow
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedStage
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedUserScreen
import com.duckduckmoosedesign.cpkid.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameScreen(override val game: LibGDXGame): AdvancedUserScreen() {

    private val parmezan = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(62)
    private val font     = fontGenerator_Kalam.generateFont(parmezan)

    // Actor
    private val aBack      = AButton(this, AButton.Static.Type.Back)
    private val aRecordLbl = Label(MenuScreen.recorDSMAN.toString(), Label.LabelStyle(font, Color.WHITE))

    // Body
    private val bItemList = List(12) { BItem(this) }
    private val bLeft     = BLeft(this)
    private val bRight    = BRight(this)
    private val bStatic   = BStatic(this)
    private val bParamon  = BParamon(this)

    // Joint
    private val jPrismatic_Left  = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)
    private val jPrismatic_Right = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)
    private val jMotor_Left      = AbstractJoint<MotorJoint, MotorJointDef>(this)
    private val jMotor_Right     = AbstractJoint<MotorJoint, MotorJointDef>(this)

    private val jPrismatic_Paramon = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)
    private val jMotor_Paramon     = AbstractJoint<MotorJoint, MotorJointDef>(this)

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(12)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.loadAss.BEDROOM.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addAcatacors()
        createB_Item()

        createB_Left()
        createB_Right()
        createB_Static()
        createB_Paramon()

        createJ_Prismatic()
        createJ_Motor()

        createJ_Paramon()
    }

    // Add
    private fun AdvancedStage.addAcatacors() {
        addActor(aBack)
        aBack.apply {
            setBounds(1309f, 21f, 202f, 96f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(Image(game.allAss.hibiskus).apply { setBounds(623f, 776f, 282f, 89f) })
        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(691f, 779f, 145f,72f)
            setAlignment(Align.center)
        }
    }

    // Bodies ------------------------------------------------------------------------

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.id = BodyId.ITEM
            bItem.collisionList.addAll(arrayOf(BodyId.ITEM, BodyId.TRIANGLE, BodyId.PARAMON))

            bItem.create(0f, HEIGHT+70, 125f, 125f)

            var timer = 0f
            bItem.renderBlockArray.add(AbstractBody.RenderBlock {
                timer += it
                if (timer >= 1) {
                    timer = 0f
                    if((bItem.body?.position?.y ?: 0f) <= 0f) {
                        if (bItem.isOnStart.getAndSet(false)) {
                            itemFlow.tryEmit(bItem)
                        }
                    }
                }
            })

            itemFlow.tryEmit(bItem)
        }

        val startPos = Vector2()

        coroutine?.launch {
            itemFlow.collect { bItem ->
                bItem.body?.apply {
                    setLinearVelocity(0f, 0f)
                    isAwake      = false
                    gravityScale = 0f

                    runGDX {
                        // Left or Right
                        startPos.x = (if (Random.nextBoolean()) (70..280) else (1250..1460)).random().toFloat()

                        setTransform(startPos.apply { y = HEIGHT+70 }.toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((150..400L).random())

                bItem.body?.apply {
                    gravityScale = 1f
                    isAwake = true
//                    applyLinearImpulse(Vector2(0f, -(10..20).random().toFloat()), worldCenter, true)
                }
            }
        }

    }

    private fun createB_Left() {
        bLeft.create(82f, 264f, 386f, 428f)
    }

    private fun createB_Right() {
        bRight.create(1222f, 264f, 386f, 428f)
    }

    private fun createB_Static() {
        bStatic.create(-34f, -34f, 68f, 68f)
    }

    private fun createB_Paramon() {
        bParamon.apply {
            id = BodyId.PARAMON
            collisionList.addAll(arrayOf(BodyId.ITEM, BodyId.TRIANGLE))
        }
        bParamon.create(611f, 0f, 306f, 379f)

        bParamon.beginContactBlockArray.add(AbstractBody.ContactBlock {
            if (it.id == BodyId.ITEM) {
                it as BItem
                if (it.isOnStart.getAndSet(false)) {

                    game.soundUtil.apply { play(cip) }

                    MenuScreen.recorDSMAN += (100..300).random()
                    aRecordLbl.setText(MenuScreen.recorDSMAN)
                    itemFlow.tryEmit(it)
                }
            }
        })
    }

    // Joints ------------------------------------------------------------------------

    private fun createJ_Prismatic() {
        jPrismatic_Left.create(PrismaticJointDef().apply {
            bodyA = bStatic.body
            bodyB = bLeft.body

            localAnchorB.set(82f.toB2, 0f)
            localAxisA.set(0f, 1f)

            enableLimit = true
            lowerTranslation = 0f
            upperTranslation = 506f.toB2
        })
        jPrismatic_Right.create(PrismaticJointDef().apply {
            bodyA = bStatic.body
            bodyB = bRight.body

            localAnchorA.set(WIDTH_BOX2D, 0f)
            localAnchorB.set(305f.toB2, 0f)
            localAxisA.set(0f, 1f)

            enableLimit = true
            lowerTranslation = 0f
            upperTranslation = 506f.toB2
        })
    }

    private fun createJ_Motor() {
        jMotor_Left.create(MotorJointDef().apply {
            bodyA = bStatic.body
            bodyB = bLeft.body

            linearOffset.set((-82f).toB2, 235f.toB2)

            maxForce = 10_000f
        })
        jMotor_Right.create(MotorJointDef().apply {
            bodyA = bStatic.body
            bodyB = bRight.body

            linearOffset.set(1222f.toB2, 235f.toB2)

            maxForce = 10_000f
        })
    }

    private fun createJ_Paramon() {
        jPrismatic_Paramon.create(PrismaticJointDef().apply {
            bodyA = bStatic.body
            bodyB = bParamon.body

            enableLimit = true
            lowerTranslation = 0f
            upperTranslation = 1200f.toB2
        })
        jMotor_Paramon.create(MotorJointDef().apply {
            bodyA = bStatic.body
            bodyB = bParamon.body

            linearOffset.set(611f.toB2, 0f)

            maxForce = 500f
        })
    }


}