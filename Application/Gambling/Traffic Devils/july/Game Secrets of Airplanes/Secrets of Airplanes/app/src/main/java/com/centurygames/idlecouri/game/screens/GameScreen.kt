package com.centurygames.idlecouri.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.MotorJoint
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.centurygames.idlecouri.game.LibGDXGame
import com.centurygames.idlecouri.game.actors.AButton
import com.centurygames.idlecouri.game.box2d.AbstractBody
import com.centurygames.idlecouri.game.box2d.AbstractJoint
import com.centurygames.idlecouri.game.box2d.BodyId
import com.centurygames.idlecouri.game.box2d.bodies.BItem
import com.centurygames.idlecouri.game.box2d.bodies.BPlane
import com.centurygames.idlecouri.game.box2d.bodies.BStatic
import com.centurygames.idlecouri.game.utils.*
import com.centurygames.idlecouri.game.utils.actor.animHide
import com.centurygames.idlecouri.game.utils.actor.animShow
import com.centurygames.idlecouri.game.utils.advanced.AdvancedStage
import com.centurygames.idlecouri.game.utils.advanced.AdvancedUserScreen
import com.centurygames.idlecouri.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameScreen(override val game: LibGDXGame) : AdvancedUserScreen() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(17)
    private val font          = fontGenerator_JotiOne.generateFont(fontParameter)

    // Actor
    private val pintelImg      = Image(game.alpha.kkk)
    private val aBack          = AButton(this, AButton.Static.Type.Back)
    private val aRecord1Lbl    = Label("0", Label.LabelStyle(font, Color.valueOf("F8F808")))
    private val aRecord2Lbl    = Label("0", Label.LabelStyle(font, Color.valueOf("373ED2")))
    private val aRecord3Lbl    = Label("0", Label.LabelStyle(font, Color.valueOf("680818")))

    // Body
    private val bItemList = List(15) { BItem(this) }
    private val bPlane    = BPlane(this)

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(15)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.lobster.lister.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Plane()
        createJ_Motor()

        createB_Item()

        addAZB()
    }

    // Add
    private fun AdvancedStage.addAZB() {
        addActor(pintelImg)
        pintelImg.setBounds(30f, 981f, 552f, 119f)


        addActor(aBack)
        aBack.apply {
            setBounds(212f, 20f, 204f, 59f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActors(aRecord1Lbl, aRecord2Lbl, aRecord3Lbl)
        aRecord1Lbl.apply {
            setBounds(290f, 986f, 31f, 23f)
            setAlignment(Align.center)
        }
        aRecord2Lbl.apply {
            setBounds(404f, 989f, 31f, 23f)
            setAlignment(Align.center)
        }
        aRecord3Lbl.apply {
            setBounds(518f, 989f, 31f, 23f)
            setAlignment(Align.center)
        }

    }

    // Create Body ------------------------------------------------------------------------

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.id = BodyId.ITEM
            bItem.collisionList.add(BodyId.PLANE)
            bItem.create(0f, HEIGHT + 115, 115f, 115f)

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
                        val xxx = (80..500).random().toFloat()
                        setTransform(startPos.set(xxx, HEIGHT + 100).toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((250..450L).random())

                runGDX {
                    bItem.body?.apply {
                        gravityScale = 1f
                        isAwake = true
                        if (Random.nextBoolean()) applyTorque(7f, false) else applyTorque(-7f, false)
                        applyLinearImpulse(Vector2(0f, -1f), worldCenter, true)
                    }
                }
            }
        }

    }

    private fun createB_Plane() {
        bPlane.apply {
            id = BodyId.PLANE
            collisionList.addAll(arrayOf(BodyId.ITEM))

            create(210f, 145f, 207f, 149f)

            beginContactBlockArray.add(AbstractBody.ContactBlock { enemy ->
                when (enemy.id) {
                    BodyId.ITEM -> {
                        enemy as BItem

                        if (enemy.isOnStart.getAndSet(false)) {
                            game.soundUtil.apply {
                                game.soundUtil.apply { play(item, 12f) }
                            }

                            MenuScreen.counter_1 += (50..300).random()
                            MenuScreen.counter_2 += (50..300).random()
                            MenuScreen.counter_3 += (50..300).random()
                            aRecord1Lbl.setText(MenuScreen.counter_1)
                            aRecord2Lbl.setText(MenuScreen.counter_2)
                            aRecord3Lbl.setText(MenuScreen.counter_3)
                            itemFlow.tryEmit(enemy)
                        }
                    }
                }
            })
        }
    }

    private fun createJ_Motor() {
        val bst = BStatic(this)
        bst.create(-50f, -50f, 100f, 100f)

        AbstractJoint<MotorJoint, MotorJointDef>(this).create(MotorJointDef().apply {
            bodyA = bst.body
            bodyB = bPlane.body

            linearOffset.set(Vector2(210f, 145f).toB2)
            maxForce  = 80f
            maxTorque = 100f
        })
    }

}