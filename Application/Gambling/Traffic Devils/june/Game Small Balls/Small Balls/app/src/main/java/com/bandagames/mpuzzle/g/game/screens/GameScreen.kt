package com.bandagames.mpuzzle.g.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.bandagames.mpuzzle.g.game.LibGDXGame
import com.bandagames.mpuzzle.g.game.actors.AButton
import com.bandagames.mpuzzle.g.game.box2d.AbstractBody
import com.bandagames.mpuzzle.g.game.box2d.AbstractJoint
import com.bandagames.mpuzzle.g.game.box2d.BodyId
import com.bandagames.mpuzzle.g.game.box2d.bodies.BItem
import com.bandagames.mpuzzle.g.game.box2d.bodies.BStatic
import com.bandagames.mpuzzle.g.game.box2d.bodies.BStrelki
import com.bandagames.mpuzzle.g.game.box2d.bodies.BTris
import com.bandagames.mpuzzle.g.game.utils.*
import com.bandagames.mpuzzle.g.game.utils.actor.animHide
import com.bandagames.mpuzzle.g.game.utils.actor.animShow
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedStage
import com.bandagames.mpuzzle.g.game.utils.advanced.AdvancedUserScreen
import com.bandagames.mpuzzle.g.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GameScreen(override val game: LibGDXGame) : AdvancedUserScreen() {

    companion object {
        var RECORD = 0
    }

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "Points Balls:.").setSize(62)
    private val font          = fontGenerator_Kenia.generateFont(fontParameter)

    // Actor
    private val aBack      = AButton(this, AButton.Static.Type.Back)
    private val aRecordLbl = Label("Points Balls: ${RECORD.toPointsFormat()}", Label.LabelStyle(font, Color.WHITE))

    // Body
    private val bItemList = List(30) { BItem(this) }
    private val bStatic   = BStatic(this)
    private val bStrelki  = BStrelki(this)
    private val bTrisList = List(4) { BTris(this, it) }.shuffled()


    // Joint
    private val jPrismatic_Strelki = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)
    private val jRevolute_List     = List(4) { AbstractJoint<RevoluteJoint, RevoluteJointDef>(this) }

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(30)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.Aoll.begi.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Item()

        createB_Static()
        createB_Strelki()
        createB_Tris()

        createJ_Strelki()
        createJ_RevoluteList()

        addBobiki()
    }

    // Add
    private fun AdvancedStage.addBobiki() {
        addActor(aBack)
        aBack.apply {
            setBounds(25f, 1768f, 224f, 129f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(376f, 1776f, 440f, 62f)
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
                collisionList.addAll(arrayOf(BodyId.TRIANGULARIA, BodyId.STRELKI, BodyId.ITEM))
            }
            bItem.create(0f, HEIGHT + 100, 90f, 90f)

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
                                (45..1035).random().toFloat(),
                                HEIGHT + 100
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
                delay((50..120L).random())

                bItem.body?.apply {
                    gravityScale = 1f
                    isAwake = true
                    applyLinearImpulse(Vector2(0f, -(1..10).random().toFloat()), worldCenter, true)
                }
            }
        }

    }

    private fun createB_Static() {
        bStatic.create(-121f, -65f, 243f, 243f)
    }

    private fun createB_Strelki() {
        bStrelki.apply {
            id = BodyId.STRELKI
            collisionList.add(BodyId.ITEM)
        }
        bStrelki.beginContactBlockArray.add(AbstractBody.ContactBlock {
            if (it.id == BodyId.ITEM) {
                it as BItem
                if (it.isOnStart.getAndSet(false)) {

                    game.soundUtil.apply { play(ake, 100f) }

                    RECORD += (1000..5000).random()
                    aRecordLbl.setText("Points Balls: \n${RECORD.toPointsFormat()}")
                    itemFlow.tryEmit(it)
                }
            }
        })
        bStrelki.create(391f, 56f, 298f, 170f)
    }

    private fun createB_Tris() {
        val poss = listOf(
            Vector2(875f, 1433f),
            Vector2(297f, 1049f),
            Vector2(659f, 722f),
            Vector2(25f, 379f),
        )
        bTrisList.onEachIndexed { index, bTris ->
            bTris.apply {
                id = BodyId.TRIANGULARIA
                collisionList.add(BodyId.ITEM)
            }
            bTris.create(poss[index], Vector2(187f, 187f))
        }
    }

    // Joints ------------------------------------------------------------------------

    private fun createJ_Strelki() {
        jPrismatic_Strelki.create(PrismaticJointDef().apply {
            bodyA = bStatic.body
            bodyB = bStrelki.body

            enableLimit      = true
            lowerTranslation = 0f
            upperTranslation = (WIDTH_UI-298).toB2
        })
    }

    private fun createJ_RevoluteList() {
        val poss = listOf(
            Vector2(969f, 1526f),
            Vector2(391f, 1142f),
            Vector2(753f, 815f),
            Vector2(122f, 472f),
        )
        jRevolute_List.onEachIndexed { index, jnt ->
            jnt.create(RevoluteJointDef().apply {
                bodyA = bStatic.body
                bodyB = bTrisList[index].body

                localAnchorA.set(poss[index].toB2)
            })
        }
    }

}