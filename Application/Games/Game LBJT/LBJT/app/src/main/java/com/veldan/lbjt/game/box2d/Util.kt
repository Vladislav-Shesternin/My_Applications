package com.veldan.lbjt.game.box2d

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Joint
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import java.lang.ArithmeticException

@JvmName("destroyAllAbstractBody")
fun List<AbstractBody>.destroyAll(time: Long = 0) {
    onEach { it.startDestroy(time) }
}

@JvmName("destroyAllAbstractJoint")
fun List<AbstractJoint<out Joint>>.destroyAll(time: Long = 0) {
    onEach { it.startDestroy(time) }
}

@JvmName("destroyAllAbstractBodyGroup")
fun List<AbstractBodyGroup>.destroyAll(time: Long = 0) {
    onEach { it.destroy(time) }
}

fun World.bodies(): Array<Body> {
    val array = Array<Body>()
    getBodies(array)
    return array
}

fun World.joints(): Array<Joint> {
    val array = Array<Joint>()
     getJoints(array)
    return array
}