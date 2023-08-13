package com.veldan.lbjt.game.box2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Joint
import com.badlogic.gdx.physics.box2d.JointDef
import com.veldan.lbjt.game.utils.*
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AbstractBodyGroup: Destroyable {
    abstract val screenBox2d    : AdvancedBox2dScreen
    abstract val standartW      : Float

    private val createdBodyList  = mutableListOf<AbstractBody>()

    protected val standardizer = SizeStandardizer()

    var coroutine: CoroutineScope? = null
        private set

    val position  = Vector2()
    val size      = Vector2()

    protected fun finishCreate(block: () -> Unit = {}) {
        createdBodyList.onEachIndexed { index, abstractBody -> abstractBody.createdBlock = {
            if (index == createdBodyList.lastIndex) block()
        } }
    }

    open fun requestToCreate(position: Vector2, size: Vector2, block: () -> Unit = {}) {
        this.position.set(position)
        this.size.set(size)

        coroutine = CoroutineScope(Dispatchers.Default)
        standardizer.standardize(standartW, size.x)
    }

    fun createBody(body: AbstractBody, pos: Vector2, size: Vector2, block: () -> Unit = {}) {
        standardizer.scope { body.requestToCreate(position.cpy().add(pos.toStandart), size.toStandart, block) }
        createdBodyList.add(body)
    }

    fun createBody(body: AbstractBody, x: Float, y: Float, w: Float, h: Float, block: () -> Unit = {}) {
        createBody(body, Vector2(x, y), Vector2(w, h), block)
    }

    fun <T : JointDef> createJoint(joint: AbstractJoint<out Joint, T>, jointDef: T, block: () -> Unit = {}) {
        joint.requestToCreate(jointDef, block)
    }

    override fun dispose(isDestroy: Boolean, block: () -> Unit) {
        cancelCoroutinesAll(coroutine)
        createdBodyList.disposeAll(block)
    }

    override fun requestToDestroy(block: () -> Unit) {
        cancelCoroutinesAll(coroutine)
        createdBodyList.destroyAll(block)
    }

    protected fun Vector2.subCenter(body: Body): Vector2 = standardizer.scope {
        this@subCenter.toStandart.toB2.sub((body.userData as AbstractBody).center)
    }

}