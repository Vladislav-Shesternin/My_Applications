package com.veldan.gamebox2d.game.box2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Joint
import com.badlogic.gdx.physics.box2d.JointDef
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef
import com.veldan.gamebox2d.game.utils.Size
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen

abstract class AbstractBodyGroup {
    abstract val screenBox2d: AdvancedBox2dScreen

    private val destroyBodyList  = mutableListOf<AbstractBody>()
    private val destroyJointList = mutableListOf<AbstractJoint<out Joint>>()

    val position = Vector2()



    open fun create(position: Vector2) {
        this.position.set(position)
    }

    open fun destroy() {
        destroyBodyList.destroyAll()
        destroyJointList.destroyAll()
    }

    fun createBody(body: AbstractBody, position: Vector2, size: Size) {
        body.create(position, size)
        destroyBodyList.add(body)
    }

    fun createJoint(joint: AbstractJoint<Joint>, jointDef: JointDef) {
        joint.create(jointDef)
        destroyJointList.add(joint)
    }

}