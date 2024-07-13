package com.my.cooking.chef.kitchen.craze.fe.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.MotorJoint
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.my.cooking.chef.kitchen.craze.fe.game.LibGDXGame
import com.my.cooking.chef.kitchen.craze.fe.game.box2d.AbstractBody
import com.my.cooking.chef.kitchen.craze.fe.game.box2d.AbstractJoint
import com.my.cooking.chef.kitchen.craze.fe.game.box2d.BodyId
import com.my.cooking.chef.kitchen.craze.fe.game.box2d.bodies.*
import com.my.cooking.chef.kitchen.craze.fe.game.utils.*
import com.my.cooking.chef.kitchen.craze.fe.game.utils.actor.animHide
import com.my.cooking.chef.kitchen.craze.fe.game.utils.actor.animShow
import com.my.cooking.chef.kitchen.craze.fe.game.utils.actor.setOnClickListener
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedStage
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedUserScreen
import com.my.cooking.chef.kitchen.craze.fe.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameScreen(override val game: LibGDXGame) : AdvancedUserScreen() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(45)
    private val font          = fontGenerator_Alice.generateFont(fontParameter)

    private var HP_COUNT = 3

    // Actor
    private val resultPanelImg = Image(game.allOl.panalka)
    private val aRecordLbl     = Label("0", Label.LabelStyle(font, Color.WHITE))
    private val aBack          = Image(game.allOl.menu)
    private val aHP_List = List(HP_COUNT) { Image(game.allOl.srdce) }

    // Body
    private val bLeftRT   = BVRect(this)
    private val bRightRT  = BVRect(this)
    private val bTopRT    = BHRect(this)

    private val bStatic = BStatic(this)
    private val bBtn    = BBtn(this)
    private val bBall   = BBall(this)

    private val bItemList = List(10) { BItem(this) }

    // Joints
    private val jMotor = AbstractJoint<MotorJoint, MotorJointDef>(this)

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(10)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.loal.listB.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Static()

        createB_RECT()
        createB_Btn()

        createJ_Motor()

        createB_Ball()

        createB_Item()
        addAzibater()
    }

    // Add
    private fun AdvancedStage.addAzibater() {
        addActor(resultPanelImg)
        resultPanelImg.setBounds(165f, 872f, 211f, 77f)


        addActor(aBack)
        aBack.apply {
            setBounds(10f, 886f, 65f, 62f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(190f, 888f, 161f, 51f)
            setAlignment(Align.center)
        }

        var nx = 162f
        aHP_List.onEach { img ->
            addActor(img)
            img.setBounds(nx, 17f, 62f, 60f)
            nx += (15+62)
        }
    }

    // Create Body ------------------------------------------------------------------------

    var rcrd = 0

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.id = BodyId.ITEM
            bItem.collisionList.add(BodyId.BALL)
            bItem.create(0f, HEIGHT + 150, 90f, 90f)

            var timer = 0f
            bItem.renderBlockArray.add(AbstractBody.RenderBlock {
                timer += it
                if (timer >= 1) {
                    timer = 0f
                    if ((bItem.body?.position?.y ?: 0f) <= 0f) {
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
                        val xxx = (48..450).random().toFloat()
                        setTransform(startPos.set(xxx, HEIGHT + 150).toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((1000..2000L).random())

                runGDX {
                    bItem.body?.apply {
                        gravityScale = 1f
                        isAwake = true
                        if (Random.nextBoolean()) applyTorque(10_000f, false) else applyTorque(-10_000f, false)
                        applyLinearImpulse(Vector2(0f, -1f), worldCenter, true)
                    }
                }
            }
        }

    }

    private fun createB_RECT() {
        listOf(bLeftRT, bRightRT, bTopRT).onEach {
            it.id = BodyId.RECT
            it.collisionList.addAll(arrayOf(BodyId.BALL, BodyId.PLTF))
        }
        bLeftRT.create(-43f, 0f, 43f, 960f)
        bRightRT.create(WIDTH_UI, 0f, 43f, 960f)
        bTopRT.create(-40f, HEIGHT_UI   , 620f, 54f)
    }

    private fun createB_Static() {
        bStatic.create(-50f, -50f, 100f, 100f)
    }

    private fun createB_Btn() {
        bBtn.apply {
            id = BodyId.PLTF
            collisionList.addAll(arrayOf(BodyId.RECT, BodyId.BALL))

            create(137f, 113f, 266f, 63f)
        }
    }

    private fun createB_Ball() {
        bBall.apply {
            id = BodyId.BALL
            collisionList.addAll(arrayOf(BodyId.RECT, BodyId.PLTF, BodyId.ITEM))

            create(220f, 371f, 100f, 100f)

            beginContactBlockArray.add(AbstractBody.ContactBlock { enemy ->
                if (enemy.id == BodyId.ITEM) {
                    enemy as BItem

                    if (enemy.isOnStart.getAndSet(false)) {
                        game.soundUtil.apply {
                            game.soundUtil.apply { play(take, 80f) }
                        }

                        rcrd += (500..3000).random()
                        aRecordLbl.setText(rcrd)
                        itemFlow.tryEmit(enemy)
                    }
                }
            })

            var timer = 0f
            renderBlockArray.add(AbstractBody.RenderBlock {
                timer += it
                if (timer >= 1) {
                    timer = 0f
                    if ((body?.position?.y ?: 0f) <= 0f) {
                        if (isOnStart.getAndSet(false)) {

                            HP_COUNT -= 1
                            if (HP_COUNT < 0) {
                                stageUI.root.animHide(TIME_ANIM) {
                                    game.navigationManager.back()
                                }
                            } else {

                                val count = 3-HP_COUNT
                                aHP_List.take(count).onEach { hp -> hp.animHide(0.3f) }

                                game.soundUtil.apply { play(fail, 50f) }

                                body?.setLinearVelocity(0f, 0f)
                                body?.setTransform(Vector2(220f + 50f, 371f + 50f).toB2, 0f)
                                actor?.addAction(Actions.sequence(
                                    Actions.delay(0.4f),
                                    Actions.run { isOnStart.set(true) }
                                ))

                            }

                        }
                    }
                }
            })
        }
    }

    // Create Joint ------------------------------------------------------------------------

    private fun createJ_Motor() {
        jMotor.create(MotorJointDef().apply {
            bodyA = bStatic.body
            bodyB = bBtn.body

            linearOffset.set(Vector2(270f, 145f).toB2)

            maxForce         = (bodyB.mass * 400)
            maxTorque        = 200_000f
            correctionFactor = 0.4f
        })
    }

}