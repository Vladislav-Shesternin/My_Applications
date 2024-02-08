package com.vbbb.time.game.game.box2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.vbbb.time.game.game.utils.Layout
import com.vbbb.time.game.game.utils.RADTODEG
import com.vbbb.time.game.game.utils.Size
import com.vbbb.time.game.game.utils.advanced.AdvancedBox2dScreen
import com.vbbb.time.game.game.utils.advanced.AdvancedGroup
import com.vbbb.time.game.util.cancelCoroutinesAll
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

    val size     = Size()
    val position = Vector2()

    var coroutine: CoroutineScope? = null
        private set

    val scale  by lazy { screenBox2d.sizeConverterUIToBox.getScale(size.width) }
    val center by lazy { screenBox2d.worldUtil.bodyEditor.getOrigin(name, scale) }

    var body: Body? = null
        private set

    var beginContactBlock: (AbstractBody) -> Unit = {}
    var endContactBlock  : (AbstractBody) -> Unit = {}
    var renderBlock      : (Float) -> Unit        = {}


    open fun render(deltaTime: Float) {
        renderActor()
        renderBlock(deltaTime)
    }

    open fun destroy() {
        if (body != null) {
            cancelCoroutinesAll(coroutine)
            actor?.dispose()
            collisionList.clear()

            with(screenBox2d.worldUtil) {
                world.destroyBody(body)
                body = null
            }
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
        body?.let { b ->
            actor?.apply {
                screenBox2d.sizeConverterBoxToUI.apply {
                    x = getSizeX(b.position.x - center.x)
                    y = getSizeY(b.position.y - center.y)
                    setOrigin(getSizeX(center.x), getSizeY(center.y))
                }
                rotation = b.angle * RADTODEG
            }
        }
    }

    private fun createBody() {
        body = screenBox2d.worldUtil.world.createBody(bodyDef).apply { userData = this@AbstractBody }
        screenBox2d.worldUtil.bodyEditor.attachFixture(body!!, name, fixtureDef, scale)
        actor?.let { addActor() }
    }


    fun create(layoutData: Layout.LayoutData) {
        create(layoutData.position(), layoutData.size())
    }

    fun create(x: Float, y: Float, w: Float, h: Float) {
        create(Vector2(x, y), Size(w, h))
    }

    fun create(position: Vector2, size: Size) {
        this.position.set(position)
        this.size.set(size)

        bodyDef.position.set(screenBox2d.sizeConverterUIToBox.getSize(position).add(center))

        coroutine = CoroutineScope(Dispatchers.Default)
        createBody()
    }

    fun startDestroy(time: Long = 0) {
        coroutine?.launch {
            delay(time)
            screenBox2d.worldUtil.destroyBodyList.add(this@AbstractBody)
        }
    }

}