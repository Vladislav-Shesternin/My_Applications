package qbl.bisriymyach.QuickBall.sudams

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Joint
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array

private val tmpArrayBody = Array<Body>()

fun World.bodies(): Array<Body> {
    getBodies(tmpArrayBody)
    return tmpArrayBody
}

fun Collection<Destroyable>.destroyAll() = onEach { it.dodo() }

interface Destroyable {
    fun dodo()
}