package atest.btest.lbjttest.game.box2d.bodiesGroup.gear

import atest.btest.lbjttest.game.box2d.AbstractBodyGroup
import atest.btest.lbjttest.game.box2d.AbstractJoint
import atest.btest.lbjttest.game.box2d.BodyId
import atest.btest.lbjttest.game.box2d.bodies.objects.BCObject
import atest.btest.lbjttest.game.utils.advanced.AdvancedBox2dScreen
import atest.btest.lbjttest.game.utils.region
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef

class BGGearRevoluteJoint(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val standartW = 231f

    // Body
    private val bStaticCircle = BCObject(screenBox2d, BodyDef.BodyType.StaticBody)
    val bDynamicCircle        = BCObject(screenBox2d, BodyDef.BodyType.DynamicBody)

    // Joint
    val jRevolute = AbstractJoint<RevoluteJoint, RevoluteJointDef>(screenBox2d)

    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        initB_Static()
        initB_Dynamic()

        createB_Dynamic()
        createB_Static()

        createJ_Revolute()
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
        arrayOf(bDynamicCircle).onEach { it.apply {
            id = BodyId.Game.DYNAMIC
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.Game.STATIC))

            update(screenBox2d.game.assets.GEAR.region)
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        createBody(bStaticCircle, 97f, 97f, 38f, 38f)
    }

    private fun createB_Dynamic() {
        createBody(bDynamicCircle, 0f, 0f, 231f, 231f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Revolute() {
        createJoint(jRevolute, RevoluteJointDef().apply {
            bodyA = bStaticCircle.body
            bodyB = bDynamicCircle.body
            collideConnected = false
        })
    }

}