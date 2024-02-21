package com.elastic.couben.game.box2d.bodiesGroup

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef
import com.elastic.couben.game.box2d.AbstractBodyGroup
import com.elastic.couben.game.box2d.bodies.BJoint
import com.elastic.couben.game.box2d.bodies.BOrb
import com.elastic.couben.game.box2d.destroyAll
import com.elastic.couben.game.utils.Size
import com.elastic.couben.game.utils.addNew
import com.elastic.couben.game.utils.advanced.AdvancedBox2dScreen

class BGTest(override val screenBox2d: AdvancedBox2dScreen): AbstractBodyGroup() {

    private val bOrbBotR = BOrb(screenBox2d, BOrb.Type.RED)
    private val bOrbTopG = BOrb(screenBox2d, BOrb.Type.GREEN)
    private val bOrbTopR = BOrb(screenBox2d, BOrb.Type.RED)
    private val bOrbBotG = BOrb(screenBox2d, BOrb.Type.GREEN)

    private val bOrbCenter = BOrb(screenBox2d, BOrb.Type.RED)

    private val bLeftJoint   = BJoint(screenBox2d)
    private val bRightJoint  = BJoint(screenBox2d)
    private val bTopJoint    = BJoint(screenBox2d)
    private val bBottomJoint = BJoint(screenBox2d)

    private var jLeft      : DistanceJoint? = null
    private var jBottomLeft: DistanceJoint? = null
    private var jTopLeft   : DistanceJoint? = null

    private var jRight      : DistanceJoint? = null
    private var jBottomRight: DistanceJoint? = null
    private var jTopRight   : DistanceJoint? = null

    private var jTop     : DistanceJoint? = null
    private var jLeftTop : DistanceJoint? = null
    private var jRightTop: DistanceJoint? = null

    private var jBottom     : DistanceJoint? = null
    private var jLeftBottom : DistanceJoint? = null
    private var jRightBottom: DistanceJoint? = null

    private var jLeftDiagonal: DistanceJoint? = null



    override fun create(position: Vector2) {
        super.create(position)
        // body
        createB_OrbBotR()
        createB_OrbTopG()
        createB_OrbTopR()
        createB_OrbBotG()

        createB_LeftJoint()
        createB_RightJoint()
        createB_TopJoint()
        createB_BottomJoint()

        createB_Center()

        // joint
        createJ_Left()
        createJ_BottomLeft()
        createJ_TopLeft()

        createJ_Right()
        createJ_TopRight()
        createJ_BottomRight()

        createJ_Top()
        createJ_LeftTop()
        createJ_RightTop()

        createJ_Bottom()
        createJ_LeftBottom()
        createJ_RightBottom()

       // createJ_LeftDiagonal()

        createJ_Center()
    }

    override fun destroy() {
        super.destroy()
        // body
        listOf(
            bOrbBotR, bOrbTopG, bOrbTopR, bOrbBotG,
            bLeftJoint, bRightJoint, bTopJoint, bBottomJoint,
        ).destroyAll()

        // joint
        listOfNotNull(
            jLeft, jBottomLeft, jTopLeft,
            jRight, jBottomRight, jTopRight,
            jTop, jLeftTop, jRightTop,
            jBottom, jLeftBottom, jRightBottom,
            jLeftDiagonal,
        ).destroyAll(screenBox2d)
    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_OrbBotR() {
        bOrbBotR.apply {
            create(this@BGTest.position.addNew(0f, 0f), Size(72f, 72f))
        }
    }

    private fun createB_OrbTopG() {
        bOrbTopG.apply {
            create(this@BGTest.position.addNew(0f, 122f), Size(72f, 72f))
        }
    }

    private fun createB_OrbTopR() {
        bOrbTopR.apply {
            create(this@BGTest.position.addNew(135f, 122f), Size(72f, 72f))
        }
    }

    private fun createB_OrbBotG() {
        bOrbBotG.apply {
            create(this@BGTest.position.addNew(135f, 0f), Size(72f, 72f))
        }
    }

    private fun createB_LeftJoint() {
        bLeftJoint.apply {
            create(this@BGTest.position.addNew(36f, 30f), Size(135f, 13f))
        }
    }

    private fun createB_RightJoint() {
        bRightJoint.apply {
            create(this@BGTest.position.addNew(36f, 30f), Size(135f, 13f))
        }
    }

    private fun createB_TopJoint() {
        bTopJoint.apply {
            create(this@BGTest.position.addNew(36f, 30f), Size(135f, 13f))
        }
    }

    private fun createB_BottomJoint() {
        bBottomJoint.apply {
            create(this@BGTest.position.addNew(36f, 30f), Size(135f, 13f))
        }
    }

    private fun createB_Center() {
        bOrbCenter.apply {
            create(this@BGTest.position.addNew(89f, 82f), Size(30f, 30f))
        }
    }

    // ------------------------------------------------------------------------
    // Create Joint
    // ------------------------------------------------------------------------

    private fun createJ_Left() {
        DistanceJointDef().apply {
            bodyA = bOrbBotR.body
            bodyB = bOrbTopG.body
            length = screenBox2d.sizeConverterUIToBox.getSizeY(135f)

            jLeft = world.createJoint(this) as DistanceJoint
        }
    }

    private fun createJ_Right() {
        DistanceJointDef().apply {
            bodyA = bOrbTopR.body
            bodyB = bOrbBotG.body
            length = screenBox2d.sizeConverterUIToBox.getSizeY(135f)

            jRight = world.createJoint(this) as DistanceJoint
        }
    }

    private fun createJ_Top() {
        DistanceJointDef().apply {
            bodyA = bOrbTopG.body
            bodyB = bOrbTopR.body
            length = screenBox2d.sizeConverterUIToBox.getSizeY(135f)

            jTop = world.createJoint(this) as DistanceJoint
        }
    }

    private fun createJ_Bottom() {
        DistanceJointDef().apply {
            bodyA = bOrbBotR.body
            bodyB = bOrbBotG.body
            length = screenBox2d.sizeConverterUIToBox.getSizeY(135f)

            jBottom = world.createJoint(this) as DistanceJoint
        }
    }

    private fun createJ_BottomLeft() {
        DistanceJointDef().apply {
            bodyA = bOrbBotR.body
            bodyB = bLeftJoint.body
            localAnchorB.set(screenBox2d.sizeConverterUIToBox.getSize(0f, 4f))
            length = 0.1f

            jBottomLeft = world.createJoint(this) as DistanceJoint
        }
    }

    private fun createJ_TopLeft() {
        DistanceJointDef().apply {
            bodyA = bOrbTopG.body
            bodyB = bLeftJoint.body
            localAnchorB.set(screenBox2d.sizeConverterUIToBox.getSize(131f, 4f))
            length = 0.1f

            jTopLeft = world.createJoint(this) as DistanceJoint
        }
    }

    private fun createJ_TopRight() {
        DistanceJointDef().apply {
            bodyA = bOrbTopR.body
            bodyB = bRightJoint.body
            localAnchorB.set(screenBox2d.sizeConverterUIToBox.getSize(0f, 4f))
            length = 0.1f

            jTopRight = world.createJoint(this) as DistanceJoint
        }
    }

    private fun createJ_BottomRight() {
        DistanceJointDef().apply {
            bodyA = bOrbBotG.body
            bodyB = bRightJoint.body
            localAnchorB.set(screenBox2d.sizeConverterUIToBox.getSize(131f, 4f))
            length = 0.1f

            jBottomRight = world.createJoint(this) as DistanceJoint
        }
    }

    private fun createJ_LeftTop() {
        DistanceJointDef().apply {
            bodyA = bOrbTopG.body
            bodyB = bTopJoint.body
            localAnchorB.set(screenBox2d.sizeConverterUIToBox.getSize(0f, 4f))
            length = 0.1f

            jLeftTop = world.createJoint(this) as DistanceJoint
        }
    }

    private fun createJ_RightTop() {
        DistanceJointDef().apply {
            bodyA = bOrbTopR.body
            bodyB = bTopJoint.body
            localAnchorB.set(screenBox2d.sizeConverterUIToBox.getSize(131f, 4f))
            length = 0.1f

            jRightTop = world.createJoint(this) as DistanceJoint
        }
    }

    private fun createJ_LeftBottom() {
        DistanceJointDef().apply {
            bodyA = bOrbBotR.body
            bodyB = bBottomJoint.body
            localAnchorB.set(screenBox2d.sizeConverterUIToBox.getSize(0f, 4f))
            length = 0.1f

            jLeftBottom = world.createJoint(this) as DistanceJoint
        }
    }

    private fun createJ_RightBottom() {
        DistanceJointDef().apply {
            bodyA = bOrbBotG.body
            bodyB = bBottomJoint.body
            localAnchorB.set(screenBox2d.sizeConverterUIToBox.getSize(131f, 4f))
            length = 0.1f

            jRightBottom = world.createJoint(this) as DistanceJoint
        }
    }

    // Diagonal

    private fun createJ_LeftDiagonal() {
        DistanceJointDef().apply {
            bodyA = bOrbTopG.body
            bodyB = bOrbBotG.body
            length = screenBox2d.sizeConverterUIToBox.getSizeX(190f)

            jRightBottom = world.createJoint(this) as DistanceJoint
        }
    }

    private fun createJ_Center() {
        listOf(bOrbBotR, bOrbTopG, bOrbTopR, bOrbBotG).onEach { orb ->
            DistanceJointDef().apply {
                bodyA = orb.body
                bodyB = bOrbCenter.body
                length = screenBox2d.sizeConverterUIToBox.getSizeX(94.7f)
                frequencyHz = 4f
                collideConnected = true
                world.createJoint(this)
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    fun right() {
        bOrbBotR.body.apply {
            applyLinearImpulse(
                Vector2(100f, 0f),
                bOrbCenter.body.worldCenter,
                true
            )
        }
    }

    fun bottom() {
        bOrbBotR.body.apply {
            applyLinearImpulse(
                Vector2(0f, -100f),
                bOrbCenter.body.worldCenter,
                true
            )
        }
    }

    fun left() {
        bOrbBotR.body.apply {
            applyLinearImpulse(
                Vector2(-100f, 0f),
                bOrbCenter.body.worldCenter,
                true
            )
        }
    }

    fun top() {
        bOrbBotR.body.apply {
            applyLinearImpulse(
                Vector2(0f, 100f),
                bOrbCenter.body.worldCenter,
                true
            )
        }
    }

}