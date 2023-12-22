package atest.btest.lbjttest.game.box2d.bodiesGroup.revolute

import atest.btest.lbjttest.game.actors.image.AImage
import atest.btest.lbjttest.game.box2d.AbstractBodyGroup
import atest.btest.lbjttest.game.box2d.AbstractJoint
import atest.btest.lbjttest.game.box2d.BodyId
import atest.btest.lbjttest.game.box2d.bodies.objects.BHObject
import atest.btest.lbjttest.game.box2d.bodies.objects.BVObject
import atest.btest.lbjttest.game.utils.advanced.AdvancedBox2dScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedGroup
import atest.btest.lbjttest.game.utils.advanced.AdvancedStage
import atest.btest.lbjttest.game.utils.toB2
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef

class BGRevoluteJoint_1(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val standartW = 205f

    // Actor
    private val aAnchorImg = AImage(screenBox2d, screenBox2d.game.assets.ANCHOR)

    // Body
    private val bStaticVertical    = BVObject(screenBox2d, BodyDef.BodyType.StaticBody)
    private val bDynamicHorizontal = BHObject(screenBox2d, BodyDef.BodyType.DynamicBody)

    // Joint
    private val jRevolute = AbstractJoint<RevoluteJoint, RevoluteJointDef>(screenBox2d)

    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        initB_Static()
        initB_Dynamic()

        createB_Static()
        createB_Dynamic()

        createJ_Revolute()

        screenBox2d.stageUI.addAnchorImg()
    }

    // Add Actor
    private fun AdvancedStage.addAnchorImg() {
        addActor(aAnchorImg)
        aAnchorImg.setBoundsStandart(162f, 28f, 15f, 15f)
    }

    // ---------------------------------------------------
    // Init
    // ---------------------------------------------------

    private fun initB_Static() {
        arrayOf(bStaticVertical).onEach { it.apply {
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
        createBody(bStaticVertical, 135f, 35f, 70f, 170f)
    }

    private fun createB_Dynamic() {
        createBody(bDynamicHorizontal, 0f, 0f, 170f, 70f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Revolute() {
        createJoint(jRevolute, RevoluteJointDef().apply {
            bodyA = bStaticVertical.body
            bodyB = bDynamicHorizontal.body
            collideConnected = false

            localAnchorA.set(Vector2(35f, 0f).subCenter(bodyA))
            localAnchorB.set(Vector2(170f, 35f).subCenter(bodyB))
        })
    }

}