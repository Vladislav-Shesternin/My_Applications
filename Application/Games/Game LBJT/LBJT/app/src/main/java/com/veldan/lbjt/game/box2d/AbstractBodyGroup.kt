package com.veldan.lbjt.game.box2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Joint
import com.badlogic.gdx.physics.box2d.JointDef
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Disposable
import com.veldan.lbjt.game.utils.*
import com.veldan.lbjt.game.utils.actor.setBounds
import com.veldan.lbjt.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.lbjt.util.cancelCoroutinesAll
import com.veldan.lbjt.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AbstractBodyGroup: Destroyable {
    abstract val screenBox2d: AdvancedBox2dScreen
    abstract val standartW  : Float

    protected val bodyList      = mutableListOf<AbstractBody>()
    protected val disposableSet = mutableSetOf<Disposable>()

    private val standardizer = SizeStandardizer()
    val Vector2.toStandart get() = standardizer.scope { toStandart }
    val Float.toStandart   get() = standardizer.scope { toStandart }

    var coroutine: CoroutineScope? = null
        private set

    val position  = Vector2()
    val size      = Vector2()

    private val tmpPositionA = Vector2()
    private val tmpPositionB = Vector2()
    private val tmpAnchorA   = Vector2()
    private val tmpAnchorB   = Vector2()

    open fun create(x: Float, y: Float, w: Float, h: Float) {
        position.set(x,y)
        size.set(w,h)

        coroutine = CoroutineScope(Dispatchers.Default)
        standardizer.standardize(standartW, w)
    }

    override fun destroy() {
        log("destroy: ${this::class.java.name.substringAfterLast('.')}")
        cancelCoroutinesAll(coroutine)
        disposableSet.disposeAll()
        bodyList.destroyAll()
    }

    fun createBody(body: AbstractBody, pos: Vector2, size: Vector2) {
        body.create(position.cpy().add(pos.toStandart), size.toStandart)
        bodyList.add(body)
    }

    fun createBody(body: AbstractBody, x: Float, y: Float, w: Float, h: Float) {
        createBody(body, Vector2(x, y), Vector2(w, h))
    }

    fun <T : JointDef> createJoint(joint: AbstractJoint<out Joint, T>, jointDef: T) {
        joint.create(jointDef)
    }

    protected fun Vector2.subCenter(body: Body): Vector2 = toStandart.toB2.sub((body.userData as AbstractBody).center)

    protected fun Actor.setBoundsStandart(x: Float, y: Float, width: Float, height: Float) {
        setBounds(position.cpy().add(Vector2(x,y).toStandart), Vector2(width, height).toStandart)
    }

    protected fun drawJoint(
        alpha: Float,
        bodyA: AbstractBody,
        bodyB: AbstractBody,
        anchorA: Vector2,
        anchorB: Vector2,
    ) {
        screenBox2d.drawerUtil.drawer.line(
            tmpPositionA.set(bodyA.body?.position).add(tmpAnchorA.set(anchorA).subCenter(bodyA.body!!)).toUI,
            tmpPositionB.set(bodyB.body?.position).add(tmpAnchorB.set(anchorB).subCenter(bodyB.body!!)).toUI,
            GameColor.joint.apply { a = alpha }, JOINT_WIDTH
        )
    }

}