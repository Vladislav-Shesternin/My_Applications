package atest.btest.lbjttest.game.box2d.bodiesGroup.distance

import atest.btest.lbjttest.game.box2d.AbstractBodyGroup
import atest.btest.lbjttest.game.box2d.AbstractJoint
import atest.btest.lbjttest.game.box2d.BodyId
import atest.btest.lbjttest.game.box2d.bodies.objects.BCObject
import atest.btest.lbjttest.game.box2d.bodies.objects.BHObject
import atest.btest.lbjttest.game.utils.advanced.AdvancedBox2dScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedGroup
import atest.btest.lbjttest.game.utils.toB2
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef

class BGDistanceJoint(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val standartW = 170f

    // Body
    private val bStaticHorizontal = BHObject(screenBox2d, BodyDef.BodyType.StaticBody)
    private val bDynamicCircle    = BCObject(screenBox2d, BodyDef.BodyType.DynamicBody)

    // Joint
    private val jDistance = AbstractJoint<DistanceJoint, DistanceJointDef>(screenBox2d)

    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        initB_Static()
        initB_Dynamic()

        createB_Static()
        createB_Dynamic()

        createJ_Distance()
    }

    // ---------------------------------------------------
    // Init
    // ---------------------------------------------------

    private fun initB_Static() {
        arrayOf(bStaticHorizontal).onEach { it.apply {
            id  = BodyId.Game.STATIC
            collisionList.add(BodyId.Game.DYNAMIC)
        } }
    }

    private fun initB_Dynamic() {
        arrayOf(bDynamicCircle).onEach { it.apply {
            id = BodyId.Game.DYNAMIC
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.Game.STATIC))
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        createBody(bStaticHorizontal, 0f, 367f, 170f, 70f)
    }

    private fun createB_Dynamic() {
        createBody(bDynamicCircle, 0f, 0f, 170f, 170f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Distance() {
        createJoint(jDistance, DistanceJointDef().apply {
            bodyA = bStaticHorizontal.body
            bodyB = bDynamicCircle.body
            collideConnected = true

            localAnchorA.set(Vector2(84f, 3f).subCenter(bodyA))
            localAnchorB.set(Vector2(84f, 167f).subCenter(bodyB))

            length = 0f//203f.toStandart.toB2
            frequencyHz = 0.5f
            dampingRatio = 5f
        })

        bStaticHorizontal.actor?.let {it.preDrawArray.add(AdvancedGroup.Static.Drawer { alpha -> jDistance.drawJoint(alpha) }) }
    }

}