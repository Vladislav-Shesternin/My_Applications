package com.earlymorningstudio.championsofa.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.earlymorningstudio.championsofa.game.LibGDXGame
import com.earlymorningstudio.championsofa.game.actors.AButton
import com.earlymorningstudio.championsofa.game.box2d.AbstractBody
import com.earlymorningstudio.championsofa.game.box2d.AbstractJoint
import com.earlymorningstudio.championsofa.game.box2d.BodyId
import com.earlymorningstudio.championsofa.game.box2d.bodies.BItem
import com.earlymorningstudio.championsofa.game.box2d.bodies.BStatic
import com.earlymorningstudio.championsofa.game.box2d.bodies.BParasha
import com.earlymorningstudio.championsofa.game.utils.*
import com.earlymorningstudio.championsofa.game.utils.actor.animHide
import com.earlymorningstudio.championsofa.game.utils.actor.animShow
import com.earlymorningstudio.championsofa.game.utils.actor.setOnClickListener
import com.earlymorningstudio.championsofa.game.utils.advanced.AdvancedStage
import com.earlymorningstudio.championsofa.game.utils.advanced.AdvancedUserScreen
import com.earlymorningstudio.championsofa.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameScreen(override val game: LibGDXGame) : AdvancedUserScreen() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(49)
    private val font          = fontGenerator_KellySlab.generateFont(fontParameter)

    // Actor
    private val resultPanelImg = Image(game.LICHIKO.asdddd)
    private val aRecordLbl     = Label("0", Label.LabelStyle(font, Color.valueOf("0CF5FD")))
    private val aBack          = AButton(this, AButton.Static.Type.Back)

    // Body
    private val bItemList = List(30) { BItem(this) }
    private val bStatic   = BStatic(this)
    private val bParasha  = BParasha(this)


    // Joint
    private val jPrismatic_Parasha = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(30)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.SRAKA.PUNDIC.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {

        createB_Static()
        createB_Parasha()

        createB_Item()

        createJ_Par()

        addEleven()
    }

    // Add
    private fun AdvancedStage.addEleven() {
        addActor(resultPanelImg)
        resultPanelImg.setBounds(42f, 25f, 295f, 157f)


        addActor(aBack)
        aBack.apply {
            setBounds(1183f, 25f, 295f, 157f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(120f, 73f, 138f, 60f)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    var counter = 0

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.id = BodyId.ITEM
            bItem.create(0f, HEIGHT + 300, 115f, 115f)

            bItem.actor?.setOnClickListener {
                if (bItem.isOnStart.getAndSet(false)) {
                    game.soundUtil.apply { play(bonus, 20f) }

                    counter += (100..200).random()
                    aRecordLbl.setText(counter)
                    itemFlow.tryEmit(bItem)
                }
            }

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
                        setTransform(startPos.set(0f, HEIGHT + 300).toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((90..500L).random())

                runGDX {
                    bItem.body?.apply {
                        setTransform(bParasha.body?.position, 0f)
                        gravityScale = 1f
                        isAwake = true
                        val xxx = if(Random.nextBoolean()) -1 * (1..15).random().toFloat() else (1..15).random().toFloat()
                        applyLinearImpulse(Vector2(xxx, (5..10).random().toFloat()), worldCenter, true)
                    }
                }
            }
        }

    }

    private fun createB_Static() {
        bStatic.create(77f, 600f, 124f, 124f)
    }

    private fun createB_Parasha() {
        bParasha.id = BodyId.PAR
        bParasha.create(592f, 585f, 335f, 260f)

        coroutine?.launch {
            var isF = true
            while (isActive) {
                runGDX {
                    if (isF) {
                        bParasha.body?.apply {
                            setLinearVelocity(0f, 0f)
                            applyLinearImpulse(Vector2((40..60).random().toFloat(), 0f), worldCenter, false) }
                    } else {
                        bParasha.body?.apply {
                            setLinearVelocity(0f, 0f)
                            applyLinearImpulse(Vector2(-(40..60).random().toFloat(), 0f), worldCenter, false) }
                    }
                    isF = !isF
                }
                delay((1000..1500).random().toLong())
            }
        }
    }

    // Joints ------------------------------------------------------------------------

    private fun createJ_Par() {
        jPrismatic_Parasha.create(PrismaticJointDef().apply {
            bodyA = bStatic.body
            bodyB = bParasha.body

            enableLimit = true
            lowerTranslation = 0f.toB2
            upperTranslation = (WIDTH_UI - 320).toB2
        })
    }

}