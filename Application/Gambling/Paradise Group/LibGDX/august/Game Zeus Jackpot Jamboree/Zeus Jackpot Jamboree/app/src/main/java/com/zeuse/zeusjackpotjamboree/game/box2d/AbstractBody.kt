package com.zeuse.zeusjackpotjamboree.game.box2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.Joint
import com.badlogic.gdx.physics.box2d.JointDef
import com.zeuse.zeusjackpotjamboree.game.utils.*
import com.zeuse.zeusjackpotjamboree.game.utils.actor.setBounds
import com.zeuse.zeusjackpotjamboree.game.utils.actor.setOrigin
import com.zeuse.zeusjackpotjamboree.game.utils.actor.setPosition
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedBox2dScreen
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedGroup
import com.zeuse.zeusjackpotjamboree.util.cancelCoroutinesAll
import com.zeuse.zeusjackpotjamboree.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AbstractBody: Destroyable {

    abstract val screenBox2d: AdvancedBox2dScreen
    abstract val name       : String
    abstract val bodyDef    : BodyDef
    abstract val fixtureDef : FixtureDef

    open var actor : AdvancedGroup? = null
    open var id    : String         = BodyId.NONE
    open val collisionList          = mutableListOf<String>()

    val size     = Vector2()
    val position = Vector2()

    val scale  get() = size.x.toB2
    val center get() = screenBox2d.worldUtil.bodyEditor.getOrigin(name, scale)

    var body: Body? = null
        private set
    var coroutine: CoroutineScope? = null
        private set

    var beginContactBlock: (AbstractBody) -> Unit = {}
    var endContactBlock  : (AbstractBody) -> Unit = {}
    var renderBlock      : (Float)        -> Unit = {}

    private val createdBlockList   = mutableListOf<(AbstractBody) -> Unit>()
    private val destroyedBlockList = mutableListOf<() -> Unit>()

    var createdBlock: (AbstractBody) -> Unit = {}
        set(value) {
            createdBlockList.add(value)
        }
    var destroyedBlock: () -> Unit = {}
        set(value) {
            destroyedBlockList.add(value)
        }

    var isDisposeActor = true


    fun render(deltaTime: Float) {
        transformActor()
        renderBlock(deltaTime)
    }

    fun beginContact(contactBody: AbstractBody) {
        beginContactBlock(contactBody)
    }

    fun endContact(contactBody: AbstractBody) {
        endContactBlock(contactBody)
    }

    override fun dispose(isDestroy: Boolean, block: () -> Unit) {
        if (body != null) {
            cancelCoroutinesAll(coroutine)
            coroutine = null

            if (isDisposeActor) {
                actor?.dispose()
                actor = null
            }

            collisionList.clear()

            body?.jointList?.onEach { (it.joint.userData as AbstractJoint<out Joint, out JointDef>).dispose(false) }
            if (isDestroy) {
                screenBox2d.worldUtil.world.destroyBody(body)
                destroyedBlockList.onEach { it.invoke() }.clear()
            }
            body = null

            block()
        }
    }

    fun createInWorld() {
        if (body == null) {
            body = screenBox2d.worldUtil.world.createBody(bodyDef).apply { userData = this@AbstractBody }
            screenBox2d.worldUtil.bodyEditor.attachFixture(body!!, name, fixtureDef, scale)
            actor?.let { addActor() }

            isDisposeActor = true

            createdBlockList.onEach { it.invoke(this) }.clear()
        }
    }

    fun requestToCreate(position: Vector2, size: Vector2, block: () -> Unit = {}) {
        createdBlock = {
            log("created: $name")
            block()
        }

        this.position.set(position)
        this.size.set(size)

        bodyDef.position.set(position.toB2.add(center))
        coroutine = CoroutineScope(Dispatchers.Default)

        screenBox2d.worldUtil.createBodyList.add(this@AbstractBody)
    }

    fun requestToCreate(layoutData: Layout.LayoutData, block: () -> Unit = {}) {
        requestToCreate(layoutData.position(), layoutData.size(), block)
    }

    fun requestToCreate(x: Float, y: Float, w: Float, h: Float, block: () -> Unit = {}) {
        requestToCreate(Vector2(x, y), Vector2(w, h), block)
    }

    override fun requestToDestroy(block: () -> Unit) {
        destroyedBlock = { block() }
        screenBox2d.worldUtil.destroyBodyList.add(this@AbstractBody)
    }

    private fun addActor() {
        actor?.apply {
            screenBox2d.stageUI.addActor(this)
            setBounds(position, size)
        }
    }

    private fun transformActor() {
        body?.let { b ->
            actor?.apply {
                setPosition(b.position.cpy().sub(center).toUI)
                setOrigin(center.cpy().toUI)
                rotation = b.angle * RADTODEG
            }
        }
    }

}