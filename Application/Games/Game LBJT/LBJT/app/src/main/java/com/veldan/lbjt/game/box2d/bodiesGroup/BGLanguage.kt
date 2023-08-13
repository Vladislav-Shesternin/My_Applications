package com.veldan.lbjt.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.WeldJoint
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.veldan.lbjt.game.box2d.AbstractBodyGroup
import com.veldan.lbjt.game.box2d.AbstractJoint
import com.veldan.lbjt.game.box2d.BodyId.Settings.LANGUAGE
import com.veldan.lbjt.game.box2d.bodies.BDynamic
import com.veldan.lbjt.game.box2d.bodies.BLanguage
import com.veldan.lbjt.game.box2d.bodies.BStatic
import com.veldan.lbjt.game.box2d.util.CheckGroupBGLanguage
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.JOINT_WIDTH
import com.veldan.lbjt.game.utils.SizeStandardizer
import com.veldan.lbjt.game.utils.actor.enable
import com.veldan.lbjt.game.utils.actor.setOnTouchListener
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.game.utils.runGDX
import com.veldan.lbjt.game.utils.toUI
import com.veldan.lbjt.util.Once
import com.veldan.lbjt.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BGLanguage(
    override val screenBox2d: AdvancedBox2dScreen,
    val type: BLanguage.Type,
): AbstractBodyGroup() {

    override val standartW = 202f

    // Body
    private val bDynamic  = BDynamic(screenBox2d)
    private val bStatic   = BStatic(screenBox2d)
    private val bLanguage = BLanguage(screenBox2d, type)

    // Joint
    private val jWeld_DynamicLanguage = AbstractJoint<WeldJoint, WeldJointDef>(screenBox2d)
    private val jWeld_DynamicStatic   = AbstractJoint<WeldJoint, WeldJointDef>(screenBox2d)

    // Field
    private val drawer = screenBox2d.drawerUtil.drawer

    private val firstSizeStandardizer     = SizeStandardizer()
    private val onceFirstSizeStandardizer = Once()

    private var onCheckBlock: (Boolean) -> Unit = {}
    var checkGroupBGLanguage: CheckGroupBGLanguage? = null
    var isCheck = true
        private set

    override fun requestToCreate(position: Vector2, size: Vector2, block: () -> Unit) {
        super.requestToCreate(position, size, block)

        onceFirstSizeStandardizer.once { firstSizeStandardizer.standardize(standartW, size.x) }
        initB_Dynamic()
        initB_Language()

        createB_Dynamic()
        createB_Static()
        createB_Language()

        finishCreate {
            block()

            createJ_Weld_DynamicLanguage()
            createJ_Weld_DynamicStatic()
        }
    }

    // ---------------------------------------------------
    // Init
    // ---------------------------------------------------

    private fun initB_Dynamic() {
        bDynamic.apply {
            fixtureDef.density = 15f
        }
    }

    private fun initB_Language() {
        bLanguage.apply {
            id  = LANGUAGE
            collisionList.addAll(arrayOf(LANGUAGE))
        }

        bLanguage.actor?.setOnTouchListener {
            if (checkGroupBGLanguage != null) {
                if (isCheck.not()) check()
            } else {
                if (isCheck) uncheck() else check()
            }
        }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Dynamic() {
        createBody(bDynamic, 66f, if (isCheck) 330f else 410f, 70f, 70f)
    }

    private fun createB_Static() {
        createBody(bStatic, 101f, 0f, 1f, 1f)
    }

    private fun createB_Language() {
        createBody(bLanguage, 0f, if (isCheck) 233f else 313f, 202f, 265f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Weld_DynamicStatic() {
        createJoint(jWeld_DynamicStatic, WeldJointDef().apply {
            bodyA = bStatic.body
            bodyB = bDynamic.body

            localAnchorB.set(Vector2(35f, if (isCheck) -330f else -410f).subCenter(bodyB))

            frequencyHz  = 10f
            dampingRatio = 0.7f

            bLanguage.actor?.blockPreDraw = { alpha ->
                drawer.line(bodyA.position.cpy().toUI, bodyB.position.cpy().toUI, GameColor.joint.apply { a = alpha }, JOINT_WIDTH)
            }
        })
    }

    private fun createJ_Weld_DynamicLanguage() {
        createJoint(jWeld_DynamicLanguage, WeldJointDef().apply {
            bodyA = bDynamic.body
            bodyB = bLanguage.body

            localAnchorB.set(Vector2(101f, 132f).subCenter(bodyB))

            frequencyHz  = 1f
            dampingRatio = 0.5f
        })
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun setOnCheckBlock(block: (Boolean) -> Unit) {
        onCheckBlock = block
    }

    fun check() {
        if (isCheck) return

        checkGroupBGLanguage?.let {
            it.currentCheckedBGLanguage?.uncheck()
            it.currentCheckedBGLanguage = this
        }

        bLanguage.increase {
            onCheckBlock(true)
            bLanguage.isDisposeActor = false

            requestToDestroy {
                isCheck = true
                firstSizeStandardizer.scope { requestToCreate(
                    position.sub(Vector2(25f, 0f).toStandart),
                    size.add(Vector2(51f, 66f).toStandart)
                ) {
                    bLanguage.actor?.addAction(Actions.scaleTo(1f, 1f))
                    screenBox2d.game.soundUtil.apply { play(ELECTRICITY) }
                } }
            }
        }
    }

    fun uncheck() {
        if (isCheck.not()) return

        bLanguage.decrease {
            onCheckBlock(false)
            bLanguage.isDisposeActor = false

            requestToDestroy {
                isCheck = false
                firstSizeStandardizer.scope { requestToCreate(
                    position.add(Vector2(25f, 0f).toStandart),
                    size.sub(Vector2(51f, 66f).toStandart)
                ) { bLanguage.actor?.addAction(Actions.scaleTo(1f, 1f)) } }
            }
        }
    }

}