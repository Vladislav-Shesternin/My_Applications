package com.bango.weld.androit.game.box2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Joint
import com.badlogic.gdx.physics.box2d.JointDef
import com.bango.weld.androit.game.utils.Size
import com.bango.weld.androit.game.utils.SizeConverter
import com.bango.weld.androit.game.utils.addNew
import com.bango.weld.androit.game.utils.advanced.AdvancedBox2dScreen

abstract class AbstractBodyGroup {
    abstract val screenBox2d  : AdvancedBox2dScreen
    abstract val fromSize     : Size
    lateinit var sizeConverter: SizeConverter private set

    private val destroyBodyList  = mutableListOf<AbstractBody>()
    private val destroyJointList = mutableListOf<AbstractJoint<out Joint>>()

    protected val Size.toGroupSize   : Size    get() = sizeConverter.getSize(this)
    protected val Vector2.toGroupSize: Vector2 get() = sizeConverter.getSize(this)

    val position = Vector2()



    open fun create(position: Vector2, size: Size) {
        this.position.set(position)
        sizeConverter = SizeConverter(fromSize, size)
    }

    open fun destroy(time: Long) {
        destroyBodyList.destroyAll(time)
        destroyJointList.destroyAll(time)
    }

    fun createBody(body: AbstractBody, x: Float, y: Float, w: Float, h: Float) {
        createBody(body, Vector2(x, y), Size(w, h))
    }

    fun createBody(body: AbstractBody, pos: Vector2, size: Size) {
        body.create(position.addNew(pos.toGroupSize), size.toGroupSize)
        destroyBodyList.add(body)
    }

    fun createJoint(joint: AbstractJoint<out Joint>, jointDef: JointDef) {
        joint.create(jointDef)
        destroyJointList.add(joint)
    }


    protected fun Vector2.subCenter(center: Vector2): Vector2 = screenBox2d.sizeConverterUIToBox.getSize(this.toGroupSize).sub(center)

}