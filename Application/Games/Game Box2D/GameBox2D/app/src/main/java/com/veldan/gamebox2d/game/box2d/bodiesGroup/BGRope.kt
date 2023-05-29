package com.veldan.gamebox2d.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.RopeJoint
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef
import com.veldan.gamebox2d.game.box2d.AbstractBody
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.AbstractJoint
import com.veldan.gamebox2d.game.box2d.bodies.BChain
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen

class BGRope(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val fromSize = Size(43f, 359f)

    // Body
    private val bChainList = List(20) { BChain(screenBox2d, BChain.Color.RED) }

    // Joint
    private val jRopeList = List(19) { AbstractJoint<RopeJoint>(screenBox2d) }


    override fun create(position: Vector2, size: Size) {
        super.create(position, size)

        createB_Chain()

        createJ_Rope()

    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Chain() {
        var ny = 248f
        bChainList.onEach {  chain ->
            createBody(chain, 0f, ny, 43f, 111f)
            ny -= 124f
        }
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Rope() {
        jRopeList.onEachIndexed { index, aJoint ->

            if (index.inc() > bChainList.lastIndex) return

            createJoint(aJoint, RopeJointDef().apply {
                bodyA = bChainList[index].body
                bodyB = bChainList[index.inc()].body

                localAnchorA.set(Vector2(21f, 10f).subCenter((bodyA.userData as AbstractBody).center))
                localAnchorB.set(Vector2(21f, 100f).subCenter((bodyA.userData as AbstractBody).center))

                maxLength = screenBox2d.sizeConverterUIToBox.getSizeY(35f.toGroupY)
                collideConnected = true
            })
        }
    }
}