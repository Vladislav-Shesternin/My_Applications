package com.duckduckmoosedesign.cpk.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.MotorJoint
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.duckduckmoosedesign.cpk.game.LibGDXGame
import com.duckduckmoosedesign.cpk.game.actors.AButton
import com.duckduckmoosedesign.cpk.game.box2d.AbstractBody
import com.duckduckmoosedesign.cpk.game.box2d.AbstractJoint
import com.duckduckmoosedesign.cpk.game.box2d.BodyId
import com.duckduckmoosedesign.cpk.game.box2d.bodies.BAvia
import com.duckduckmoosedesign.cpk.game.box2d.bodies.BItem
import com.duckduckmoosedesign.cpk.game.box2d.bodies.BStatic
import com.duckduckmoosedesign.cpk.game.utils.*
import com.duckduckmoosedesign.cpk.game.utils.actor.animHide
import com.duckduckmoosedesign.cpk.game.utils.actor.animShow
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedStage
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedUserScreen
import com.duckduckmoosedesign.cpk.game.utils.font.FontParameter
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GameresScreen(override val game: LibGDXGame) : AdvancedUserScreen() {

    companion object {
        var planes = 0
    }

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+"Planes:").setSize(90)
    private val font          = fntScript.generateFont(fontParameter)

    // Actor
    private val aBack      = AButton(this, AButton.Static.Type.Back)
    private val aRecordLbl = Label(planes.toCount(), Label.LabelStyle(font, Color.WHITE))

    // Body
    private val bItemList = List(20) { BItem(this) }
    private val bStatic   = BStatic(this)
    private val bCircle   = BAvia(this)

    // Joint
    private val jMotor = AbstractJoint<MotorJoint, MotorJointDef>(this)

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(20)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.Mis.Firster.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Static()
        addRum()

        createB_Item()
        createB_Avia()

        createJ_Motor()
    }

    // Add
    private fun AdvancedStage.addRum() {
        addActor(aBack)
        aBack.apply {
            setBounds(1188f, 0f, 185f, 141f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        val pnlImg = Image(game.Ser.result)
        addActor(pnlImg)
        pnlImg.setBounds(417f, 696f, 616f, 141f)

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(538f, 701f, 375f, 131f)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.apply {
                id = BodyId.ITEM
                collisionList.addAll(arrayOf(BodyId.AVIA))
            }
            bItem.create(WIDTH_UI+150, -100f, 214f, 116f)

            var timer = 0f
            bItem.renderBlockArray.add(AbstractBody.RenderBlock {
                timer += it
                if (timer >= 1) {
                    timer = 0f
                    if ((bItem.body?.position?.x ?: 0f) < 0f) {
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
                    isAwake = false
                    gravityScale = 0f

                    runGDX {
                        setTransform(startPos.set(WIDTH_UI+150f, (0..400).random().toFloat()).toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((200..500L).random())

                bItem.body?.apply {
                    gravityScale = 1f
                    isAwake = true
                    applyLinearImpulse(Vector2(-(7..10).random().toFloat(), (1..5).random().toFloat()), worldCenter, true)
                }
            }
        }

    }

    private fun createB_Static() {
        bStatic.create(-50f, -50f, 100f, 100f)
    }

    private fun createB_Avia() {
        bCircle.apply {
            id = BodyId.AVIA
            collisionList.addAll(arrayOf(BodyId.ITEM))

            beginContactBlockArray.add(AbstractBody.ContactBlock {
                if (it.id == BodyId.ITEM) {
                    it as BItem
                    if (it.isOnStart.getAndSet(false)) {
                        game.soundUtil.apply { play(collect, 90f) }

                        planes += 1
                        aRecordLbl.setText("Planes: " + planes.toCount())
                        itemFlow.tryEmit(it)
                    }
                }
            })
        }
        bCircle.create(20f, 346f, 397f, 215f)
    }

    // Joints ------------------------------------------------------------------------

    private fun createJ_Motor() {
        jMotor.create(MotorJointDef().apply {
            bodyA = bStatic.body
            bodyB = bCircle.body

            maxForce  = (bodyB.mass*100)
            maxTorque = 1000f

            linearOffset.set(Vector2(100f, 346f).toB2)
        })
    }

}