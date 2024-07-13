package com.kongregate.mobile.burrit.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.kongregate.mobile.burrit.game.LibGDXGame
import com.kongregate.mobile.burrit.game.actors.AButton
import com.kongregate.mobile.burrit.game.actors.checkbox.ACheckBox
import com.kongregate.mobile.burrit.game.box2d.AbstractBody
import com.kongregate.mobile.burrit.game.box2d.AbstractJoint
import com.kongregate.mobile.burrit.game.box2d.BodyId
import com.kongregate.mobile.burrit.game.box2d.bodies.BItem
import com.kongregate.mobile.burrit.game.box2d.bodies.BPrc
import com.kongregate.mobile.burrit.game.box2d.bodies.BStatic
import com.kongregate.mobile.burrit.game.box2d.bodies.BSuperItem
import com.kongregate.mobile.burrit.game.utils.*
import com.kongregate.mobile.burrit.game.utils.actor.animHide
import com.kongregate.mobile.burrit.game.utils.actor.animShow
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedStage
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedUserScreen
import com.kongregate.mobile.burrit.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameScreen(override val game: LibGDXGame) : AdvancedUserScreen() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(40)
    private val font          = fontGenerator_Hubballi.generateFont(fontParameter)

    // Actor
    private val u123Img        = Image(game.alpha.miniotfr)
    private val aBack          = AButton(this, AButton.Static.Type.Back)
    private val aRecordLbl     = Label("0", Label.LabelStyle(font, Color.valueOf("F6ED36")))
    private val aRecord1Lbl    = Label("0", Label.LabelStyle(font, Color.valueOf("F6ED36")))
    private val aRecord2Lbl    = Label("0", Label.LabelStyle(font, Color.valueOf("F6ED36")))
    private val aRecord3Lbl    = Label("0", Label.LabelStyle(font, Color.valueOf("F6ED36")))
    private val cbPAUSE        = ACheckBox(this, ACheckBox.Static.Type.PAUSE)

    // Body
    private val bItemList      = List(10) { BItem(this) }
    private val bSuperItemList = List(10) { BSuperItem(this) }
    private val bPrc           = BPrc(this)

    // Fluid
    private val itemFlow      = MutableSharedFlow<BItem>(10)
    private val superItemFlow = MutableSharedFlow<BSuperItem>(10)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.lobster.bacMini.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Prc()
        createJ_Prsm()

        createB_Item()
        createB_SuperItem()

        addUrbanizm()
    }

    // Add
    private fun AdvancedStage.addUrbanizm() {
        addActor(u123Img)
        u123Img.setBounds(231f, 1530f, 618f, 390f)


        addActor(aBack)
        aBack.apply {
            setBounds(28f, 1743f, 185f, 74f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(473f, 1555f, 135f, 39f)
            setAlignment(Align.center)
        }

        addActors(aRecord1Lbl, aRecord2Lbl, aRecord3Lbl)
        aRecord1Lbl.apply {
            setBounds(269f, 1672f, 135f, 39f)
            setAlignment(Align.center)
        }
        aRecord2Lbl.apply {
            setBounds(472f, 1672f, 135f, 39f)
            setAlignment(Align.center)
        }
        aRecord3Lbl.apply {
            setBounds(675f, 1672f, 135f, 39f)
            setAlignment(Align.center)
        }

        addActor(cbPAUSE)
        cbPAUSE.apply {
            setBounds(918f, 1739f, 111f, 116f)
            setOnCheckListener { isPauseWorld = it }
        }

    }

    // Create Body ------------------------------------------------------------------------

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.id = BodyId.ITEM
            bItem.collisionList.add(BodyId.PRINCESSA)
            bItem.create(0f, HEIGHT + 150, 146f, 146f)

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
                        val xxx = (75..1000).random().toFloat()
                        setTransform(startPos.set(xxx, HEIGHT + 150).toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((200..500L).random())

                runGDX {
                    bItem.body?.apply {
                        gravityScale = 1f
                        isAwake = true
                        if (Random.nextBoolean()) applyTorque(10f, false) else applyTorque(-10f, false)
                        applyLinearImpulse(Vector2(0f, -1f), worldCenter, true)
                    }
                }
            }
        }

    }

    private fun createB_SuperItem() {
        bSuperItemList.onEach { bItem ->
            bItem.id = BodyId.SUPER_ITEM
            bItem.collisionList.add(BodyId.PRINCESSA)
            bItem.create(0f, HEIGHT + 250, 220f, 220f)

            var timer = 0f
            bItem.renderBlockArray.add(AbstractBody.RenderBlock {
                timer += it
                if (timer >= 1) {
                    timer = 0f
                    if ((bItem.body?.position?.y ?: 0f) <= 0f) {
                        if (bItem.isOnStart.getAndSet(false)) {
                            superItemFlow.tryEmit(bItem)
                        }
                    }
                }
            })

            superItemFlow.tryEmit(bItem)
        }

        val startPos = Vector2()

        coroutine?.launch {
            superItemFlow.collect { bItem ->
                bItem.body?.apply {
                    setLinearVelocity(0f, 0f)
                    isAwake = false
                    gravityScale = 0f

                    runGDX {
                        val xxx = (110..900).random().toFloat()
                        setTransform(startPos.set(xxx, HEIGHT + 250).toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            superItemFlow.collect { bItem ->
                delay((700..1000L).random())

                runGDX {
                    bItem.body?.apply {
                        gravityScale = 1f
                        isAwake = true
                        if (Random.nextBoolean()) applyTorque(20f, false) else applyTorque(-20f, false)
                        applyLinearImpulse(Vector2(0f, -1f), worldCenter, true)
                    }
                }
            }
        }

    }

    private fun createB_Prc() {
        bPrc.apply {
            id = BodyId.PRINCESSA
            collisionList.addAll(arrayOf(BodyId.ITEM, BodyId.SUPER_ITEM))

            create(319f, 0f, 442f, 600f)

            beginContactBlockArray.add(AbstractBody.ContactBlock { enemy ->
                when (enemy.id) {
                    BodyId.ITEM -> {
                        enemy as BItem

                        if (enemy.isOnStart.getAndSet(false)) {
                            game.soundUtil.apply {
                                game.soundUtil.apply { play(item, 80f) }
                            }

                            MenuScreen.counter_FULL += (50..300).random()
                            aRecordLbl.setText(MenuScreen.counter_FULL)
                            itemFlow.tryEmit(enemy)
                        }
                    }
                    BodyId.SUPER_ITEM -> {
                        enemy as BSuperItem

                        if (enemy.isOnStart.getAndSet(false)) {
                            game.soundUtil.apply {
                                game.soundUtil.apply { play(SUPER, 80f) }
                            }

                            MenuScreen.counter_FULL += (100..300).random()
                            MenuScreen.counter_1 += (10..30).random()
                            MenuScreen.counter_2 += (10..30).random()
                            MenuScreen.counter_3 += (10..30).random()

                            aRecordLbl.setText(MenuScreen.counter_FULL)
                            aRecord1Lbl.setText(MenuScreen.counter_1)
                            aRecord2Lbl.setText(MenuScreen.counter_2)
                            aRecord3Lbl.setText(MenuScreen.counter_3)

                            superItemFlow.tryEmit(enemy)
                        }
                    }
                }
            })
        }
    }

    private fun createJ_Prsm() {
        val bst = BStatic(this)
        bst.create(-50f, -50f, 100f, 100f)

        AbstractJoint<PrismaticJoint, PrismaticJointDef>(this).create(PrismaticJointDef().apply {
            bodyA = bst.body
            bodyB = bPrc.body

            enableLimit = true
            lowerTranslation = 0f
            upperTranslation = (WIDTH_UI-442).toB2
        })
    }

}