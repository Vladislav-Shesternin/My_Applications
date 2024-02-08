package com.williamsanteractive.jackputpasty.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef
import com.williamsanteractive.jackputpasty.game.box2d.AbstractBodyGroup
import com.williamsanteractive.jackputpasty.game.box2d.AbstractJoint
import com.williamsanteractive.jackputpasty.game.box2d.bodies.BCir
import com.williamsanteractive.jackputpasty.game.box2d.bodies.BStaticCircle
import com.williamsanteractive.jackputpasty.game.utils.DEGTORAD
import com.williamsanteractive.jackputpasty.game.utils.Size
import com.williamsanteractive.jackputpasty.game.utils.advanced.AdvancedBox2dScreen
import java.util.Random

class BGCrug(override val screenBox2d: AdvancedBox2dScreen) : AbstractBodyGroup() {

    override val fromSize = Size(131f, 131f)

    private val bStatic = BStaticCircle(screenBox2d)
    private val bCrug   = BCir(screenBox2d)

    private val jRev    = AbstractJoint<RevoluteJoint>(screenBox2d)


    override fun create(position: Vector2, size: Size) {
        super.create(position, size)

        createB_Static()
        createB_Crug()

        createJ_Rev()
    }



    // ---------------------------------------------------
    // Create Body
    // ---------------------------------------------------

    private fun createB_Static() {
        createBody(bStatic, 61f, 61f, 10f, 10f)
    }

    private fun createB_Crug() {
        createBody(bCrug, 0f, 0f, 131f, 131f)
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Rev() {
        createJoint(jRev, RevoluteJointDef().apply {
            bodyA = bStatic.body
            bodyB = bCrug.body

            enableMotor    = true
            motorSpeed     = ((100..300).shuffled().first() * DEGTORAD) * if (Random().nextBoolean()) 1 else -1
            maxMotorTorque = 10f
        })
    }


}