package com.mvgamesteam.mineta.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.mvgamesteam.mineta.game.LibGDXGame
import com.mvgamesteam.mineta.game.actors.AButton
import com.mvgamesteam.mineta.game.box2d.AbstractBody
import com.mvgamesteam.mineta.game.box2d.AbstractJoint
import com.mvgamesteam.mineta.game.box2d.BodyId
import com.mvgamesteam.mineta.game.box2d.bodies.BItem
import com.mvgamesteam.mineta.game.box2d.bodies.BStatic
import com.mvgamesteam.mineta.game.box2d.bodies.BTris
import com.mvgamesteam.mineta.game.utils.*
import com.mvgamesteam.mineta.game.utils.actor.animHide
import com.mvgamesteam.mineta.game.utils.actor.animShow
import com.mvgamesteam.mineta.game.utils.actor.setOnClickListener
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedStage
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedUserScreen
import com.mvgamesteam.mineta.game.utils.font.FontParameter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GameScreen(override val game: LibGDXGame) : AdvancedUserScreen() {

    companion object {
        var ballsPoimano = 0
    }

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars).setSize(100)
    private val font          = fontGenerator_Kavoon.generateFont(fontParameter)

    // Actor
    private val aBack      = AButton(this, AButton.Static.Type.Back)
    private val aRecordLbl = Label(ballsPoimano.toProbelchiky(), Label.LabelStyle(font, Color.WHITE))

    // Body
    private val bItemList = List(10) { BItem(this) }
    private val bStatic   = BStatic(this)
    private val bTrisList = List(7) { BTris(this, it) }.shuffled()


    // Joint
    private val jRevolute_List = List(7) { AbstractJoint<RevoluteJoint, RevoluteJointDef>(this) }

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(10)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.Sap.Splash.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createB_Item()

        createB_Static()
        createB_Tris()

        createJ_RevoluteList()

        addAllwaise()
    }

    // Add
    private fun AdvancedStage.addAllwaise() {
        addActor(aBack)
        aBack.apply {
            setBounds(1255f, 19f, 234f, 125f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM) {
                    game.navigationManager.back()
                }
            }
        }

        val pnlImg = Image(game.Jer.lpo)
        addActor(pnlImg)
        pnlImg.setBounds(493f, 8f, 507f, 110f)

        addActor(aRecordLbl)
        aRecordLbl.apply {
            setBounds(499f, 1f, 495f, 125f)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.actor?.setOnClickListener {
                game.soundUtil.apply { play(collect, 100f) }

                ballsPoimano += (500..5000).random()
                aRecordLbl.setText(ballsPoimano.toProbelchiky())
                itemFlow.tryEmit(bItem)
            }

            bItem.apply {
                id = BodyId.ITEM
                collisionList.addAll(arrayOf(BodyId.TRIANGULARIA, BodyId.STRELKI, BodyId.ITEM))
            }
            bItem.create(-200f, 0f, 127f, 127f)

            var timer = 0f
            bItem.renderBlockArray.add(AbstractBody.RenderBlock {
                timer += it
                if (timer >= 1) {
                    timer = 0f
                    if ((bItem.body?.position?.x ?: 0f) >= WIDTH_BOX2D+5) {
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
                        setTransform(startPos.set(-200f, (70..800).random().toFloat()).toB2, 0f)
                        bItem.isOnStart.set(true)
                    }
                }
            }
        }
        coroutine?.launch {
            itemFlow.collect { bItem ->
                delay((70..140L).random())

                bItem.body?.apply {
                    gravityScale = 1f
                    isAwake = true
                    applyLinearImpulse(Vector2((1..10).random().toFloat(), 0f), worldCenter, true)
                }
            }
        }

    }

    private fun createB_Static() {
        bStatic.create(-50f, -50f, 100f, 100f)
    }

    private fun createB_Tris() {
        val poss = listOf(
            Vector2(80f, 0f),
            Vector2(385f, 291f),
            Vector2(306f, 657f),
            Vector2(711f, 529f),
            Vector2(965f, 182f),
            Vector2(1255f, 370f),
            Vector2(1267f, 736f),
        )
        bTrisList.onEachIndexed { index, bTris ->
            bTris.apply {
                id = BodyId.TRIANGULARIA
                collisionList.add(BodyId.ITEM)
            }
            bTris.create(poss[index], Vector2(159f, 159f))
        }
    }

    // Joints ------------------------------------------------------------------------

    private fun createJ_RevoluteList() {
        val poss = listOf(
            Vector2(160f, 79f),
            Vector2(465f, 370f),
            Vector2(386f, 736f),
            Vector2(791f, 608f),
            Vector2(1045f, 261f),
            Vector2(1335f, 450f),
            Vector2(1347f, 815f),
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