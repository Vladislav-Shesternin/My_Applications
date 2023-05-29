package com.veldan.gamebox2d.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef
import com.veldan.gamebox2d.game.box2d.AbstractBody
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.AbstractJoint
import com.veldan.gamebox2d.game.box2d.bodies.BArm
import com.veldan.gamebox2d.game.box2d.bodies.BTrunk
import com.veldan.gamebox2d.game.utils.DEGTORAD
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.gamebox2d.game.utils.vector2
import com.veldan.gamebox2d.util.log
import kotlin.math.cos
import kotlin.math.sin

class BGPulley(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val fromSize = Size(581f, 95f)

    // Body
    private val bTrunkA = BTrunk(screenBox2d)
    private val bTrunkB = BTrunk(screenBox2d)

    // Joint
    private val jPulley = AbstractJoint<PulleyJoint>(screenBox2d)


    override fun create(position: Vector2, size: Size) {
        super.create(position, size)

        createB_Trunk()

        createJ_Pulley()

    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Trunk() {
        createBody(bTrunkA, 0f, 0f, 206f, 95f)
        createBody(bTrunkB, 0f, 375f, 206f, 95f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Pulley() {
        createJoint(jPulley, PulleyJointDef().apply {
            bodyA = bTrunkA.body
            bodyB = bTrunkB.body

            localAnchorA.set(Vector2(96f, 41f).subCenter((bodyA.userData as AbstractBody).center))
            localAnchorB.set(Vector2(96f, 41f).subCenter((bodyB.userData as AbstractBody).center))

            groundAnchorA.set(screenBox2d.sizeConverterUIToBox.getSize(601f, 387f).vector2)
            groundAnchorB.set(screenBox2d.sizeConverterUIToBox.getSize(763f, 387f).vector2)

            lengthA = 5f
            lengthB = 5f

            ratio = -1f

        })
    }
}