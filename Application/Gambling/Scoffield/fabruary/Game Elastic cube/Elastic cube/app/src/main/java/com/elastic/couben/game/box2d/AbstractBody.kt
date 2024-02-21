package com.elastic.couben.game.box2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.elastic.couben.game.utils.Size
import com.elastic.couben.game.utils.advanced.AdvancedBox2dScreen
import com.elastic.couben.game.utils.advanced.AdvancedGroup
import com.elastic.couben.utils.Once
import com.elastic.couben.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class AbstractBody {
    abstract val screenBox2d  : AdvancedBox2dScreen
    abstract var id           : BodyId
    abstract val name         : String
    abstract val bodyDef      : BodyDef
    abstract val fixtureDef   : FixtureDef
    abstract val collisionList: MutableList<BodyId>

    open val actor: AdvancedGroup? = null

    val size      = Size()
    val position  = Vector2()
    val coroutine = CoroutineScope(Dispatchers.Default)

    val scale  by lazy { screenBox2d.sizeConverterUIToBox.getScale(size.width) }
    val center by lazy { screenBox2d.worldUtil.bodyEditor.getOrigin(name, scale) }
    val body   by lazy { screenBox2d.worldUtil.world.createBody(bodyDef).apply { userData = this@AbstractBody } }

    var beginContactBlock: (AbstractBody) -> Unit = {}
    var endContactBlock  : (AbstractBody) -> Unit = {}
    var renderBlock      : (Float) -> Unit        = {}

    private val destroyOnce = Once()



    open fun render(deltaTime: Float) {
        renderActor()
        renderBlock(deltaTime)
    }

    open fun destroy() {
        cancelCoroutinesAll(coroutine)
        actor?.dispose()
        collisionList.clear()

        with(screenBox2d.worldUtil) {
            world.destroyBody(body)
            renderList.remove(this@AbstractBody)
        }
    }

    open fun beginContact(contactBody: AbstractBody) {
        beginContactBlock(contactBody)
    }

    open fun endContact(contactBody: AbstractBody) {
        endContactBlock(contactBody)
    }



    private fun addActor() {
        actor?.apply {
            screenBox2d.stageUI.addActor(this)
            setBounds(position.x, position.y, size.width, size.height)
        }
    }

    private fun renderActor() {
        actor?.apply {
            screenBox2d.sizeConverterBoxToUI.apply {
                x = getSizeX(body.position.x - center.x)
                y = getSizeY(body.position.y - center.y)
                setOrigin(getSizeX(center.x), getSizeY(center.y))
            }
            rotation = Math.toDegrees(body.angle.toDouble()).toFloat()
        }
    }

    private fun createBody() {
        screenBox2d.worldUtil.bodyEditor.attachFixture(body!!, name, fixtureDef, scale)
        screenBox2d.worldUtil.renderList.add(this)

        actor?.let { addActor() }
    }



    fun create(position: Vector2, size: Size) {
        this.position.set(position)
        this.size.set(size)

        with(screenBox2d.sizeConverterUIToBox) {
            bodyDef.position.set(
                getSizeX(position.x) + center.x,
                getSizeY(position.y) + center.y
            )
        }

        createBody()
    }

    fun startDestroy(time: Long = 0) {
        destroyOnce.once {
            coroutine.launch {
                delay(time)
                screenBox2d.worldUtil.destroyBodyList.add(this@AbstractBody)
            }
        }
    }

}