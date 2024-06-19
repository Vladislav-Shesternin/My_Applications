package com.dogbytegames.offtheroa.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.dogbytegames.offtheroa.game.LibGDXGame
import com.dogbytegames.offtheroa.game.actors.AButton
import com.dogbytegames.offtheroa.game.box2d.AbstractBody
import com.dogbytegames.offtheroa.game.box2d.AbstractJoint
import com.dogbytegames.offtheroa.game.box2d.BodyId
import com.dogbytegames.offtheroa.game.box2d.bodies.BItem
import com.dogbytegames.offtheroa.game.box2d.bodies.BStatic
import com.dogbytegames.offtheroa.game.box2d.bodies.BSobaka
import com.dogbytegames.offtheroa.game.utils.*
import com.dogbytegames.offtheroa.game.utils.actor.animHide
import com.dogbytegames.offtheroa.game.utils.actor.animShow
import com.dogbytegames.offtheroa.game.utils.actor.setOnClickListener
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedStage
import com.dogbytegames.offtheroa.game.utils.advanced.AdvancedUserScreen
import com.dogbytegames.offtheroa.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameScreen(override val game: LibGDXGame) : AdvancedUserScreen() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(53)
    private val font          = fontGenerator_LoveYaLikeASister.generateFont(fontParameter)

    // Actor
    private val resultPanelImg = Image(game.aAlibaba.result_panel)
    private val aRecordLbl     = Label("0", Label.LabelStyle(font, Color.WHITE))
    private val aBack          = AButton(this, AButton.Static.Type.Back)

    // Body
    private val bItemList = List(10) { BItem(this) }
    private val bStatic   = BStatic(this)
    private val bSobaka = BSobaka(this)


    // Joint
    private val jPrismatic_Strelki = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(10)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.aLdnr.LDR.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Item()

        createB_Static()
        createB_Sobaka()

        createJ_Sobaka()

        addSuchok()
    }

    // Add
    private fun AdvancedStage.addSuchok() {
        addActor(resultPanelImg)
        resultPanelImg.setBounds(40f, 18f, 229f, 75f)


        addActor(aBack)
        aBack.apply {
            setBounds(1134f, 18f, 229f, 75f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(78f, 23f, 153f, 66f)
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
            bItem.create(0f, HEIGHT + 300, 120f, 120f)

            bItem.actor?.setOnClickListener {
                if (bItem.isOnStart.getAndSet(false)) {
                    game.soundUtil.apply { play(bonus, 20f) }

                    counter += (10..150).random()
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
                delay((300..780L).random())

                runGDX {
                    bItem.body?.apply {
                        setTransform(bSobaka.body?.position, 0f)
                        gravityScale = 1f
                        isAwake = true
                        applyLinearImpulse(Vector2( if(Random.nextBoolean()) -1 * (1..10).random().toFloat() else (1..10).random().toFloat(), 0f), worldCenter, true)
                    }
                }
            }
        }

    }

    private fun createB_Static() {
        bStatic.create(-50f, 557f, 100f, 100f)
    }

    private fun createB_Sobaka() {
        bSobaka.id = BodyId.SOBAKA
        bSobaka.create(583f, 567f, 238f, 247f)

        coroutine?.launch {
            var isF = true
            while (isActive) {
                runGDX {
                    if (isF) {
                        bSobaka.body?.apply {
                            setLinearVelocity(0f, 0f)
                            applyLinearImpulse(Vector2((10..25).random().toFloat(), 0f), worldCenter, false) }
                    } else {
                        bSobaka.body?.apply {
                            setLinearVelocity(0f, 0f)
                            applyLinearImpulse(Vector2(-(10..25).random().toFloat(), 0f), worldCenter, false) }
                    }
                    isF = !isF
                }
                delay((800..1500).random().toLong())
            }
        }
    }

    // Joints ------------------------------------------------------------------------

    private fun createJ_Sobaka() {
        jPrismatic_Strelki.create(PrismaticJointDef().apply {
            bodyA = bStatic.body
            bodyB = bSobaka.body

            enableLimit = true
            lowerTranslation = 120f.toB2
            upperTranslation = (WIDTH_UI - 120).toB2
        })
    }

}