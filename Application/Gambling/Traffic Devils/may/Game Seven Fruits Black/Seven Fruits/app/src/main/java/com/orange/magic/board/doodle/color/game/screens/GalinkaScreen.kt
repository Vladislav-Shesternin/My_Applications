package com.orange.magic.board.doodle.color.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.orange.magic.board.doodle.color.game.LidaGame
import com.orange.magic.board.doodle.color.game.actors.button.AButton
import com.orange.magic.board.doodle.color.game.box2d.AbstractBody
import com.orange.magic.board.doodle.color.game.box2d.AbstractJoint
import com.orange.magic.board.doodle.color.game.box2d.BodyId
import com.orange.magic.board.doodle.color.game.box2d.bodies.BItem
import com.orange.magic.board.doodle.color.game.box2d.bodies.BMal
import com.orange.magic.board.doodle.color.game.box2d.bodies.BStatic
import com.orange.magic.board.doodle.color.game.box2d.bodiesGroup.BGBorders
import com.orange.magic.board.doodle.color.game.box2d.destroyAll
import com.orange.magic.board.doodle.color.game.utils.*
import com.orange.magic.board.doodle.color.game.utils.actor.animHide
import com.orange.magic.board.doodle.color.game.utils.actor.animShow
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedStage
import com.orange.magic.board.doodle.color.game.utils.advanced.AdvancedUserScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GalinkaScreen(override val game: LidaGame): AdvancedUserScreen(game) {

    // Actor
    private val aMenu = AButton(
        this,
        AButton.Static.Type.MNU
    )

    // BodyGroup
    private val bgBorders = BGBorders(this)

    // Body
    private val bStatic   = BStatic(this)
    private val bMal      = BMal(this)
    private val bItemList = List(6) { BItem(this, it) }

    // Joint
    private val jPrismatic = AbstractJoint<PrismaticJoint, PrismaticJointDef>(this)

    // Fluid
    private val itemFlow = MutableSharedFlow<BItem>(6)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.dgop.meduna.region)
        super.show()
        stageUI.root.animShow(TIMI_TERNER)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMenu()

        createBG_Borders()

        createB_Static()
        createB_Mal()
        createJ_Prismatic()

        createB_Item()
    }

    override fun dispose() {
        listOf(bgBorders).destroyAll()
        super.dispose()
    }

    // Add
    private fun AdvancedStage.addMenu() {
        addActors(aMenu)

        aMenu.apply {
            setBounds(34f, 1773f, 112f, 112f)
            setOnClickListener {
                stageUI.root.animHide(TIMI_TERNER) { game.navigationManager.back() }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------
    private fun createBG_Borders() {
        bgBorders.create(0f,0f,WIDTH,HEIGHT)
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Static() {
        bStatic.apply {
            id = BodyId.BORDERS
            collisionList.add(BodyId.MAL)
        }
        bStatic.create(-158f, 29f, 315f, 315f)
    }

    private fun createB_Mal() {
        bMal.apply {
            id = BodyId.MAL
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.ITEM))

            fixtureDef.restitution = 0.5f
        }
        bMal.create(383f, 29f, 315f, 315f)

        var timeBarier = 0L
        bMal.beginContactBlockArray.add(AbstractBody.ContactBlock {
            when(it.id) {
                BodyId.BORDERS -> {
                    if (currentTimeMinus(timeBarier) > 300) {
                        timeBarier = System.currentTimeMillis()
                        game.soundUtil.apply { play(barier) }
                    }
                }
                BodyId.ITEM -> {
                    it as BItem
                    if (it.isOnStart.getAndSet(false)) {
                        it.startEffect()
                        game.soundUtil.apply { play(si) }
                        bMal.body?.apply { applyLinearImpulse((-30..30).random().toFloat(), 0f, worldCenter.x, worldCenter.y, true) }
                        itemFlow.tryEmit(it)

                        WeRcordeScreen.apply { listItemCounter[it.index] = listItemCounter[it.index] + 1 }
                    }
                }
            }
        })
    }

    private fun createB_Item() {
        bItemList.onEach { bItem ->
            bItem.apply {
                id = BodyId.ITEM
                collisionList.add(BodyId.MAL)
            }
            bItem.create(0f, HEIGHT+300, 293f, 293f)

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
                        setTransform(
                            startPos.set(
                                (150..850).random().toFloat(),
                                HEIGHT+300
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
                delay((500..1000L).random())

                bItem.body?.apply {
                    gravityScale = 1f
                    isAwake = true
                    applyLinearImpulse(Vector2(0f, -(10..20).random().toFloat()), worldCenter, true)
                }
            }
        }

    }

    // Joint
    private fun createJ_Prismatic() {
        jPrismatic.create(PrismaticJointDef().apply {
            bodyA = bStatic.body
            bodyB = bMal.body
        })
    }

}