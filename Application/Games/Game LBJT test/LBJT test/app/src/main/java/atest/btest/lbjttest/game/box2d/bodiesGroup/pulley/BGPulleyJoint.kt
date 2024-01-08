package atest.btest.lbjttest.game.box2d.bodiesGroup.pulley

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
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef

class BGPulleyJoint(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val standartW = 255f

    // Actor
    private val aAnchorImg = AImage(screenBox2d, screenBox2d.game.assets.ANCHOR)

    // Body
    private val bStaticHorizontal   = BHObject(screenBox2d, BodyDef.BodyType.StaticBody)
    private val bDynamicCircleLeft  = BCObject(screenBox2d, BodyDef.BodyType.DynamicBody)
    private val bDynamicCircleRight = BCObject(screenBox2d, BodyDef.BodyType.DynamicBody)

    // Joint
    private val jPulley = AbstractJoint<PulleyJoint, PulleyJointDef>(screenBox2d)

    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        initB_Static()
        initB_Dynamic()

        createB_Static()
        createB_Dynamic()

        createJ_Pulley()

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
        arrayOf(bStaticHorizontal).onEach { it.apply {
            id  = BodyId.Game.STATIC
            collisionList.add(BodyId.Game.DYNAMIC)
        } }
    }

    private fun initB_Dynamic() {
        arrayOf(bDynamicCircleLeft, bDynamicCircleRight).onEach { it.apply {
            id = BodyId.Game.DYNAMIC
            collisionList.addAll(arrayOf(BodyId.BORDERS, BodyId.Game.STATIC, BodyId.Game.DYNAMIC))
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        //createBody(bStaticHorizontal, 43f, 255f, 170f, 70f)
    }

    private fun createB_Dynamic() {
        createBody(bDynamicCircleLeft, 0f, 0f, 85f, 85f)
        createBody(bDynamicCircleRight, 170f, 0f, 85f, 85f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Pulley() {
        createJoint(jPulley, PulleyJointDef().apply {
            bodyA = bDynamicCircleLeft.body
            bodyB = bDynamicCircleRight.body
            collideConnected = true

            groundAnchorA.set(position.cpy().add(43f,255f).toB2)
            groundAnchorB.set(position.cpy().add(213f,255f).toB2)

            lengthA = 170f.toB2
            lengthB = 170f.toB2

            localAnchorA.set(Vector2(43f, 85f).subCenter(bodyA))
            localAnchorB.set(Vector2(43f, 85f).subCenter(bodyB))

            ratio = 2f
        })
    }

}