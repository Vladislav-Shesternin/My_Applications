package atest.btest.lbjttest.game.box2d.bodiesGroup.weld

import atest.btest.lbjttest.game.actors.image.AImage
import atest.btest.lbjttest.game.box2d.AbstractBodyGroup
import atest.btest.lbjttest.game.box2d.AbstractJoint
import atest.btest.lbjttest.game.box2d.BodyId
import atest.btest.lbjttest.game.box2d.bodies.objects.BCObject
import atest.btest.lbjttest.game.box2d.bodies.objects.BHObject
import atest.btest.lbjttest.game.box2d.bodies.objects.BVObject
import atest.btest.lbjttest.game.utils.DEGTORAD
import atest.btest.lbjttest.game.utils.advanced.AdvancedBox2dScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedStage
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.joints.WeldJoint
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef

class BGWeldJoint_Practical(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val standartW = 170f

    // Actor
    private val aAnchorImg = AImage(screenBox2d, screenBox2d.game.assets.ANCHOR)

    // Body
    private val bStaticCircle  = BCObject(screenBox2d, BodyDef.BodyType.StaticBody)
    private val bDynamicCircle = BCObject(screenBox2d, BodyDef.BodyType.DynamicBody)

    // Joint
    private val jWeld = AbstractJoint<WeldJoint, WeldJointDef>(screenBox2d)

    override fun create(x: Float, y: Float, w: Float, h: Float) {
        super.create(x, y, w, h)

        initB_Static()
        initB_Dynamic()

        createB_Static()
        createB_Dynamic()

        createJ_Weld()

        screenBox2d.stageUI.addAnchorImg()
    }

    // Add Actor
    private fun AdvancedStage.addAnchorImg() {
        addActor(aAnchorImg)
        aAnchorImg.setBoundsStandart(77f, 119f, 15f, 15f)
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
        } }
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        createBody(bStaticCircle, 0f, 0f, 170f, 170f)
    }

    private fun createB_Dynamic() {
        createBody(bDynamicCircle, 0f, 126f, 170f, 170f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Weld() {
        createJoint(jWeld, WeldJointDef().apply {
            bodyA = bStaticCircle.body
            bodyB = bDynamicCircle.body
            collideConnected = false

            localAnchorA.set(Vector2(85f, 126f).subCenter(bodyA))
            localAnchorB.set(Vector2(85f, 0f).subCenter(bodyB))

            referenceAngle = 45f * DEGTORAD

            frequencyHz  = 0f
            dampingRatio = 0f
        })
    }

}