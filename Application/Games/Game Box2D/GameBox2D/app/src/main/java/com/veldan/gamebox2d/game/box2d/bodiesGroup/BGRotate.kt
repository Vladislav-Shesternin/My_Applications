package com.veldan.gamebox2d.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import com.veldan.gamebox2d.game.box2d.AbstractBody
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.AbstractJoint
import com.veldan.gamebox2d.game.box2d.bodies.*
import com.veldan.gamebox2d.game.box2d.destroyAll
import com.veldan.gamebox2d.game.utils.DEGTORAD
import com.veldan.gamebox2d.game.utils.RADTODEG
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.addNew
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.gamebox2d.util.log

class BGRotate(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    override val fromSize: Size = Size(52f, 279f)

    // Body

    private val bBlueRed = BBlueRed(screenBox2d)
    private val bCircle  = BCircle(screenBox2d)

    // Body Group

    private val bgChainR  = BGChain(screenBox2d, BChain.Color.RED)
    private val bgChainB  = BGChain(screenBox2d, BChain.Color.BLUE)


    // Joint

    private val jRevolute           = AbstractJoint<RevoluteJoint>(screenBox2d)
    private val jRevolute_RedChain  = AbstractJoint<RevoluteJoint>(screenBox2d)
    private val jRevolute_BlueChain = AbstractJoint<RevoluteJoint>(screenBox2d)


    override fun create(position: Vector2, size: Size) {
        super.create(position, size)

        createB_BlueRed()
        createB_Circle()

        createBG_ChainR()
        createBG_ChainB()

        createJ_Revolute()
        createJ_Revolute_RedChain()
        createJ_Revolute_BlueChain()
    }

    override fun destroy(time: Long) {
        super.destroy(time)
        listOf(bgChainR, bgChainB).destroyAll()
    }

    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_BlueRed() {
        createBody(bBlueRed,0f, 0f,52f, 279f)
    }

    private fun createB_Circle() {
        createBody(bCircle,22f, 135f,8f, 8f)
    }

    // ---------------------------------------------------
    // Create Body Group
    // ---------------------------------------------------

    private fun createBG_ChainR() {
        bgChainR.create(position.addNew(Vector2(20f, -152f).toGroupSize), Size(12f, 181f).toGroupSize)
    }

    private fun createBG_ChainB() {
        bgChainB.create(position.addNew(Vector2(20f, 75f).toGroupSize), Size(12f, 181f).toGroupSize)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Revolute() {
        createJoint(jRevolute, RevoluteJointDef().apply {
            bodyA = bCircle.body
            bodyB = bBlueRed.body

            localAnchorA.set(Vector2(26f, 139f).subCenter(bBlueRed.center))

            enableMotor    = true
            motorSpeed     = 700 * DEGTORAD
            maxMotorTorque = 100f
        })
    }

    private fun createJ_Revolute_RedChain() {
        createJoint(jRevolute_RedChain, RevoluteJointDef().apply {
            bodyA = bBlueRed.body
            bodyB = bgChainR.bChainList.first().body

            localAnchorA.set(Vector2(26f, 26f).subCenter((bodyA.userData as AbstractBody).center))
            localAnchorB.set(
                bgChainR.run {
                    Vector2(6f, 28f).subCenter((bodyB.userData as AbstractBody).center)
                }
            )
        })
    }

    private fun createJ_Revolute_BlueChain() {
        createJoint(jRevolute_BlueChain, RevoluteJointDef().apply {
            bodyA = bBlueRed.body
            bodyB = bgChainB.bChainList.first().body

            localAnchorA.set(Vector2(26f, 253f).subCenter((bodyA.userData as AbstractBody).center))
            localAnchorB.set(
                bgChainR.run {
                    Vector2(6f, 28f).subCenter((bodyB.userData as AbstractBody).center)
                }
            )
        })
    }

}