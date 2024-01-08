package atest.btest.lbjttest.game.box2d.bodiesGroup.rope

import atest.btest.lbjttest.game.actors.image.AImage
import atest.btest.lbjttest.game.box2d.AbstractBodyGroup
import atest.btest.lbjttest.game.box2d.AbstractJoint
import atest.btest.lbjttest.game.box2d.BodyId
import atest.btest.lbjttest.game.box2d.bodies.objects.BCObject
import atest.btest.lbjttest.game.box2d.bodies.objects.BHObject
import atest.btest.lbjttest.game.box2d.bodies.objects.BVObject
import atest.btest.lbjttest.game.utils.advanced.AdvancedBox2dScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedStage
import atest.btest.lbjttest.game.utils.toB2
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef
import com.badlogic.gdx.physics.box2d.joints.RopeJoint
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef

class BGRopeJoint(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val standartW = 70f

    // Actor
    private val aAnchorImg = AImage(screenBox2d, screenBox2d.game.assets.ANCHOR)

    // Body
    private val bStaticCircle        = BCObject(screenBox2d, BodyDef.BodyType.StaticBody)
    private val bDynamicVerticalList = List(4) { BVObject(screenBox2d, BodyDef.BodyType.DynamicBody) }

    // Joint
    private val jRopeList = List(4) { AbstractJoint<RopeJoint, RopeJointDef>(screenBox2d) }

    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        initB_Static()
        initB_Dynamic()

        createB_Static()
        createB_Dynamic()

        createJ_Rope()

        //screenBox2d.stageUI.addAnchorImg()
    }

    // Add Actor
    private fun AdvancedStage.addAnchorImg() {
        addActor(aAnchorImg)
        aAnchorImg.setBoundsStandart(27f, 27f, 15f, 15f)
    }

    // ---------------------------------------------------
    // Init
    // ---------------------------------------------------

    private fun initB_Static() {
        arrayOf(bStaticCircle).onEach { it.apply {
            id  = BodyId.Game.STATIC
            collisionList.add(BodyId.Game.DYNAMIC)
        } }
    }

    private fun initB_Dynamic() {
        bDynamicVerticalList.onEach { it.apply {
            id = BodyId.Game.DYNAMIC
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.Game.STATIC))
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        createBody(bStaticCircle, 0f, 572f, 70f, 70f)
    }

    private fun createB_Dynamic() {
        var ny = 429f
        bDynamicVerticalList.onEach { bVerObj ->
            createBody(bVerObj, 15f, ny, 40f, 97f)
            ny -= (46f + 97f)
        }
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Rope() {
        createJoint(jRopeList[0], RopeJointDef().apply {
            bodyA = bStaticCircle.body
            bodyB = bDynamicVerticalList[0].body
            collideConnected = true

            localAnchorA.set(Vector2(35f, 0f).subCenter(bodyA))
            localAnchorB.set(Vector2(20f, 95f).subCenter(bodyB))

            maxLength = 46f.toB2
        })

        jRopeList.takeLast(3).onEachIndexed { index, jRope ->
            createJoint(jRope, RopeJointDef().apply {
                bodyA = bDynamicVerticalList[index].body
                bodyB = bDynamicVerticalList[index + 1].body
                collideConnected = true

                localAnchorA.set(Vector2(20f, 2f).subCenter(bodyA))
                localAnchorB.set(Vector2(20f, 95f).subCenter(bodyB))

                maxLength = 46f.toB2
            })
        }
    }

}