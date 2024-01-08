package atest.btest.lbjttest.game.box2d.bodiesGroup.friction

import atest.btest.lbjttest.game.actors.image.AImage
import atest.btest.lbjttest.game.box2d.AbstractBodyGroup
import atest.btest.lbjttest.game.box2d.AbstractJoint
import atest.btest.lbjttest.game.box2d.BodyId
import atest.btest.lbjttest.game.box2d.bodies.objects.BCObject
import atest.btest.lbjttest.game.box2d.bodies.objects.BHObject
import atest.btest.lbjttest.game.utils.DEGTORAD
import atest.btest.lbjttest.game.utils.advanced.AdvancedBox2dScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedStage
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.joints.FrictionJoint
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef

class BGFrictionJoint(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val standartW = 170f

    // Actor
    private val aAnchorImg = AImage(screenBox2d, screenBox2d.game.assets.ANCHOR)

    // Body
    private val bStaticCircle      = BCObject(screenBox2d, BodyDef.BodyType.StaticBody)
    private val bDynamicHorizontal = BHObject(screenBox2d, BodyDef.BodyType.DynamicBody)

    // Joint
    private val jFriction = AbstractJoint<FrictionJoint, FrictionJointDef>(screenBox2d)

    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        initB_Static()
        initB_Dynamic()

        createB_Static()
        createB_Dynamic()

        createJ_Friction()

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
        arrayOf(bDynamicHorizontal).onEach { it.apply {
            id = BodyId.Game.DYNAMIC
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.Game.STATIC))
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        createBody(bStaticCircle, 45f, 0f, 80f, 80f)
    }

    private fun createB_Dynamic() {
        createBody(bDynamicHorizontal, 0f, 187f, 170f, 70f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Friction() {
        createJoint(jFriction, FrictionJointDef().apply {
            bodyA = bStaticCircle.body
            bodyB = bDynamicHorizontal.body
            collideConnected = true

            localAnchorA.set(Vector2(125f, 40f).subCenter(bodyA))
            localAnchorB.set(Vector2(135f, 25f).subCenter(bodyB))

            maxForce  = 300f
            maxTorque = 300f
        })
    }

}