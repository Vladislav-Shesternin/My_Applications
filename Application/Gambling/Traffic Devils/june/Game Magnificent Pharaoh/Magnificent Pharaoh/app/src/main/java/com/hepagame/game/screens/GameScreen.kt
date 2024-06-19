package com.hepagame.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.MotorJoint
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.hepagame.game.LibGDXGame
import com.hepagame.game.box2d.AbstractBody
import com.hepagame.game.box2d.AbstractJoint
import com.hepagame.game.box2d.BodyId
import com.hepagame.game.box2d.bodies.*
import com.hepagame.game.utils.*
import com.hepagame.game.utils.actor.animHide
import com.hepagame.game.utils.actor.animShow
import com.hepagame.game.utils.actor.setOnClickListener
import com.hepagame.game.utils.advanced.AdvancedStage
import com.hepagame.game.utils.advanced.AdvancedUserScreen
import com.hepagame.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GameScreen(override val game: LibGDXGame): AdvancedUserScreen() {

    private val paramsFont = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+"Treasures:.").setSize(40)
    private val font       = fontGenerator_Kings.generateFont(paramsFont)

    // Actor
    private val aBack      = Image(game.assasinAll.bik)
    private val aRecordLbl = Label("Treasures:\n${MenuScreen.RECORDS.toBalanceFormat()}", Label.LabelStyle(font, Color.valueOf("E5D618")))

    // Body
    private val bItemList = List(12) { BItem(this) }
    private val bStatic   = BStatic(this)
    private val bParamon  = BParamon(this)

    // Joint
    private val jPrismatic_Paramon = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)
    private val jMotor_Paramon     = AbstractJoint<MotorJoint, MotorJointDef>(this)

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(12)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.assasinLoader.PAGRAN.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addAcatacors()

        createB_Static()

        createB_Paramon()
        createJ_Paramon()

        createB_Item()

    }

    // Add
    private fun AdvancedStage.addAcatacors() {
        addActor(aBack)
        aBack.apply {
            setBounds(0f, 819f, 196f, 64f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(678f, 771f, 170f,112f)
            setAlignment(Align.center)
        }
    }

    // Bodies ------------------------------------------------------------------------

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.id = BodyId.ITEM
            bItem.collisionList.addAll(arrayOf(BodyId.ITEM, BodyId.PARAMON))

            bItem.create(0f, HEIGHT+300, 162f, 162f)

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
                        startPos.x = (81..1400).random().toFloat()

                        setTransform(startPos.apply { y = HEIGHT+300 }.toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((200..700L).random())

                bItem.body?.apply {
                    gravityScale = 1f
                    isAwake = true
                }
            }
        }

    }

    private fun createB_Static() {
        bStatic.create(-50f, -50f, 100f, 100f)
    }

    private fun createB_Paramon() {
        bParamon.apply {
            id = BodyId.PARAMON
            collisionList.addAll(arrayOf(BodyId.ITEM))
        }
        bParamon.create(503f, -20f, 578f, 400f)

        bParamon.beginContactBlockArray.add(AbstractBody.ContactBlock {
            if (it.id == BodyId.ITEM) {
                it as BItem
                if (it.isOnStart.getAndSet(false)) {

                    game.soundUtil.apply { play(hit) }

                    MenuScreen.RECORDS += (500..1000).random()
                    aRecordLbl.setText("Treasures:\n${MenuScreen.RECORDS.toBalanceFormat()}")
                    itemFlow.tryEmit(it)
                }
            }
        })
    }

    // Joints ------------------------------------------------------------------------

    private fun createJ_Paramon() {
        jPrismatic_Paramon.create(PrismaticJointDef().apply {
            bodyA = bStatic.body
            bodyB = bParamon.body

            enableLimit = true
            lowerTranslation = -81f
            upperTranslation = 1200f.toB2
        })
        jMotor_Paramon.create(MotorJointDef().apply {
            bodyA = bStatic.body
            bodyB = bParamon.body

            linearOffset.set(503f.toB2, (-20f).toB2)

            maxForce = 1000f
        })
    }


}