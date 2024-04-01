package com.legojum.kangarooper.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.legojum.kangarooper.game.LibGDXGame
import com.legojum.kangarooper.game.box2d.AbstractBody
import com.legojum.kangarooper.game.box2d.AbstractJoint
import com.legojum.kangarooper.game.box2d.BodyId
import com.legojum.kangarooper.game.box2d.bodies.BKangaroo
import com.legojum.kangarooper.game.box2d.bodies.BPlatform
import com.legojum.kangarooper.game.box2d.bodies.BStatic
import com.legojum.kangarooper.game.box2d.bodies.BUser
import com.legojum.kangarooper.game.utils.TIME_ANIM
import com.legojum.kangarooper.game.utils.actor.animHide
import com.legojum.kangarooper.game.utils.actor.animShow
import com.legojum.kangarooper.game.utils.advanced.AdvancedStage
import com.legojum.kangarooper.game.utils.advanced.AdvancedUserScreen
import com.legojum.kangarooper.game.utils.font.FontParameter
import com.legojum.kangarooper.game.utils.runGDX
import com.legojum.kangarooper.game.utils.toB2
import com.legojum.kangarooper.game.utils.toUI
import com.legojum.kangarooper.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class KangarooGameScreen(override val game: LibGDXGame): AdvancedUserScreen() {

    private val params = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(65)
    private val font   = fontGenerator_AvertaDemoPE.generateFont(params)

    // Actor
    private val label   = Label("Record: 0m", Label.LabelStyle(font, Color.BLACK))
    private val lineImg = Image(game.allAssets.line)

    // Body
    private val bStatic = BStatic(this)
    private val bK      = BKangaroo(this)
    private val bUser   = BUser(this)

    private val bP1    = BPlatform(this)
    private val bListP = List(4) { BPlatform(this) }

    // Joint
    private val jPrismaticH = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)
    private val jPrismaticV = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)

    // Field
    private val platformFlow = MutableSharedFlow<BPlatform>(replay = 5)

    private val getRandomPX: Float get() = (0..794).random().toFloat()

    override fun show() {
        stageUI.root.animHide()
        super.show()
        stageUI.root.animShow(TIME_ANIM) { background2.startAnim() }
    }
    private var metr = 0

    override fun AdvancedStage.addActorsOnStageUI() {
        addLineImg()

        createB_Static()
        createB_Kangaroo()

        createB_Panel()
        createB_Platforms()

        createB_User()

        createJ_PrismaticV()
        createJ_PrismaticH()

        addLabel()

        coroutine?.launch {
            while (isActive) {
                metr++
                runGDX { label.setText("Record: ${metr}m") }
                delay((100..200L).random())
            }
        }

    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addLabel() {
        addActor(label)
        label.setBounds(338f, 1804f, 405f, 97f)
        label.setAlignment(Align.center)
    }

    private fun AdvancedStage.addLineImg() {
        addActor(lineImg)
        lineImg.setBounds(30f, 187f, 1020f, 6f)
    }

    // ---------------------------------------------------
    // create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        bStatic.create(0f, 0f, 1f, 1f)
    }

    private fun createB_Kangaroo() {
        bK.create(459f, 525f, 177f, 243f)

        val isBp = AtomicBoolean(true)

        bK.beginContactBlockArray.add(AbstractBody.ContactBlock { enemy,_ ->
            if (enemy.id == BodyId.P) {
                if (isBp.getAndSet(false)) {
                    game.soundUtil.apply { play(JUMP) }
                    bK.body?.setLinearVelocity(0f, 0f)
                    bK.body?.applyLinearImpulse(Vector2(0f, 700f), bK.body?.worldCenter, true)

                    coroutine?.launch {
                        delay(100)
                        isBp.set(true)
                    }
                }
            }
        })

        var timer = 0f
        val isFinished = AtomicBoolean(true)
        bK.renderBlockArray.add(AbstractBody.RenderBlock {
            timer += it
            if (timer >= 0.2f) {
                timer = 0f
                bK.body?.let { b ->
                    if (b.position.y <= -3f) {
                        if (isFinished.getAndSet(false)) {
                            KangarooMenuScreen.RECORD = metr
                            stageUI.root.animHide(TIME_ANIM) { game.navigationManager.back() }
                        }
                    }
                }
            }
        })
    }

    private fun createB_User() {
        bUser.create(463f, 114f, 153f, 153f)
    }

    private fun createB_Panel() {
        bP1.create(397f, 345f, 286f, 38f)

        bP1.body?.setLinearVelocity(0f, -5f)

        var timer = 0f
        bP1.renderBlockArray.add(AbstractBody.RenderBlock { time ->
            bP1.body?.let { bP ->
                if (bP.position.y <= -2f) {
                    if (bP1.atomicBoolean.getAndSet(false)) platformFlow.tryEmit(bP1)
                }

                timer += time
                if (timer >= 0.2f) {
                    timer = 0f
                    bP1.id = if (bK.body!!.position.y > (bP.position.y + 30f.toB2)) BodyId.P else BodyId.NONE
                }
            }
        })
    }

    private fun createB_Platforms() {
        val listY = listOf(707f, 1069f, 1431f, 1793f)

        bListP.onEachIndexed { index, bP ->
//            bA.addEffect()
            bP.create(getRandomPX, listY[index],286f, 38f)
            bP.body?.setLinearVelocity(0f, -5f)

            var timer = 0f
            bP.renderBlockArray.add(AbstractBody.RenderBlock { time ->
                bP.body?.let { bPBody ->
                    if (bPBody.position.y <= -2f) {
                        if (bP.atomicBoolean.getAndSet(false)) platformFlow.tryEmit(bP)
                    }

                    timer += time
                    if (timer >= 0.1f) {
                        timer = 0f
                        bP.id = if (bK.body!!.position.y > (bPBody.position.y + 30f.toB2)) BodyId.P else BodyId.NONE
                    }
                }
            })
        }

        coroutine?.launch {
            platformFlow.collect { bP ->
                runGDX {
                    bP.body?.setTransform(Vector2(getRandomPX,HEIGHT + 38f).toB2, 0f)
                    bP.atomicBoolean.set(true)
                }
            }
        }
    }

    // ---------------------------------------------------
    // create Joint
    // ---------------------------------------------------

    private fun createJ_PrismaticV() {
        jPrismaticH.create(PrismaticJointDef().apply {
            bodyA = bStatic.body
            bodyB = bUser.body

            localAnchorA.set(Vector2(540f, 187f).toB2)

            enableLimit = true
            lowerTranslation = (-470f).toB2
            upperTranslation = 470f.toB2
        })
    }

    private fun createJ_PrismaticH() {
        jPrismaticV.create(PrismaticJointDef().apply {
            bodyA = bUser.body
            bodyB = bK.body

            localAnchorB.set(Vector2(88f, 0f).toB2)

            localAxisA.set(0f, 1f)
        })
    }


}