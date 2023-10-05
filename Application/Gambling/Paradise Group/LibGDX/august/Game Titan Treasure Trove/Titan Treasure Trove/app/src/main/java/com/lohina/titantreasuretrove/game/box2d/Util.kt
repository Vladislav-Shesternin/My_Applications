package com.lohina.titantreasuretrove.game.box2d

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Joint
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Disposable

fun List<Destroyable>.disposeAll(block: () -> Unit = {}) = onEachIndexed { index, disposable ->
    disposable.dispose(false) { if (index == lastIndex) block() }
}
fun List<Destroyable>.destroyAll(block: () -> Unit = {}) = onEachIndexed { index, disposable ->
    disposable.requestToDestroy { if (index == lastIndex) block() }
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

interface Destroyable {
    fun dispose(isDestroy: Boolean = true, block: () -> Unit = {})
    fun requestToDestroy(block: () -> Unit = {})
}