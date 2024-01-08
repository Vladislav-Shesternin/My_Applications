package atest.btest.lbjttest.game.box2d.bodiesGroup.gear

import atest.btest.lbjttest.game.box2d.AbstractBodyGroup
import atest.btest.lbjttest.game.box2d.AbstractJoint
import atest.btest.lbjttest.game.box2d.BodyId
import atest.btest.lbjttest.game.box2d.bodies.objects.BCObject
import atest.btest.lbjttest.game.box2d.bodies.objects.BHObject
import atest.btest.lbjttest.game.utils.advanced.AdvancedBox2dScreen
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef

class BGGearPrismaticJoint(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val standartW = 217f

    // Body
    private val bStaticCircle = BCObject(screenBox2d, BodyDef.BodyType.StaticBody)
    val bDynamicHorizontal    = BHObject(screenBox2d, BodyDef.BodyType.DynamicBody)

    // Joint
    val jPrismatic = AbstractJoint<PrismaticJoint, PrismaticJointDef>(screenBox2d)

    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        initB_Static()
        initB_Dynamic()

        createB_Static()
        createB_Dynamic()

        createJ_Prismatic()
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
        arrayOf(bDynamicHorizontal).onEach { it.apply {
            id = BodyId.Game.DYNAMIC
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.Game.STATIC))
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        createBody(bStaticCircle, 90f, 0f, 38f, 38f)
    }

    private fun createB_Dynamic() {
        createBody(bDynamicHorizontal, 0f, 38f, 217f, 89f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Prismatic() {
        createJoint(jPrismatic, PrismaticJointDef().apply {
            bodyA = bStaticCircle.body
            bodyB = bDynamicHorizontal.body
            collideConnected = false

            localAnchorA.set(Vector2(19f, 82f).subCenter(bodyA))
        })
    }

}