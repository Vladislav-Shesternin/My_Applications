package com.internetdes.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.internetdes.game.LibGDXGame
import com.internetdes.game.actors.AButton
import com.internetdes.game.actors.AProgress
import com.internetdes.game.box2d.AbstractBody
import com.internetdes.game.box2d.AbstractJoint
import com.internetdes.game.box2d.BodyId
import com.internetdes.game.box2d.bodies.BItem
import com.internetdes.game.box2d.bodies.BStatic
import com.internetdes.game.box2d.bodies.BPrincesa
import com.internetdes.game.utils.*
import com.internetdes.game.utils.actor.animHide
import com.internetdes.game.utils.actor.animShow
import com.internetdes.game.utils.advanced.AdvancedStage
import com.internetdes.game.utils.advanced.AdvancedUserScreen
import com.internetdes.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GameScreen(override val game: LibGDXGame) : AdvancedUserScreen() {

    private val fontParameter =
        FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "%").setSize(57)
    private val font = fontGenerator_HoltwoodOneSC.generateFont(fontParameter)

    // Actor
    private val aImg = Image(game.aALL.asg)
    private val aBack = AButton(this, AButton.Static.Type.Back)
    private val aProg      = AProgress(this)
    private val aRecordLbl = Label("0%", Label.LabelStyle(font, Color.WHITE))

    // Body
    private val bItemList = List(33) { BItem(this) }
    private val bStatic   = BStatic(this)
    private val bPrincesa = BPrincesa(this)


    // Joint
    private val jPrismatic_Strelki = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(33)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.aLOA.BAGRATION.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Item()

        createB_Static()
        createB_Princessa()

        createJ_Princessa()

        addBobiki()
    }

    // Add
    private fun AdvancedStage.addBobiki() {
        addActor(aImg)
        aImg.setBounds(366f, 708f, 795f, 181f)


        addActor(aBack)
        aBack.apply {
            setBounds(9f, 11f, 123f, 123f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(aProg)
        aProg.setBounds(469f, 761f, 589f, 74f)
        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(649f, 750f, 229f, 94f)
            setAlignment(Align.center)
        }

        coroutine?.launch {
            aProg.progressPercentFlow.collect {
                runGDX {
                    aRecordLbl.setText(it.toInt().toString() + "%")
                    if (it >= 100f) {
                        stageUI.root.animHide(TIME_ANIM) {
                            game.navigationManager.navigate(GameScreen::class.java.name)
                        }
                    }
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.apply {
                id = BodyId.ITEM
                collisionList.addAll(arrayOf(BodyId.PRINCESSA, BodyId.ITEM))
            }
            bItem.create(0f, HEIGHT + 300, 168f, 168f)

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
                        setTransform(
                            startPos.set(
                                (100..1400).random().toFloat(),
                                HEIGHT + 300
                            ).toB2,
                            0f
                        )
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((100..420L).random())

                bItem.body?.apply {
                    gravityScale = 1f
                    isAwake = true
                    applyLinearImpulse(Vector2(0f, -(1..10).random().toFloat()), worldCenter, true)
                }
            }
        }

    }

    private fun createB_Static() {
        bStatic.create(-50f, -50f, 100f, 100f)
    }

    private fun createB_Princessa() {
        bPrincesa.apply {
            id = BodyId.PRINCESSA
            collisionList.add(BodyId.ITEM)
        }
        bPrincesa.beginContactBlockArray.add(AbstractBody.ContactBlock {
            if (it.id == BodyId.ITEM) {
                it as BItem
                if (it.isOnStart.getAndSet(false)) {

                    game.soundUtil.apply { play(bonus, 20f) }

                    aProg.progressPercentFlow.value += ((10..20).random() / 10f)
                    itemFlow.tryEmit(it)
                }
            }
        })
        bPrincesa.create(621f, 0f, 286f, 294f)
    }

    // Joints ------------------------------------------------------------------------

    private fun createJ_Princessa() {
        jPrismatic_Strelki.create(PrismaticJointDef().apply {
            bodyA = bStatic.body
            bodyB = bPrincesa.body

            enableLimit = true
            lowerTranslation = 0f
            upperTranslation = (WIDTH_UI - 100).toB2
        })
    }

}