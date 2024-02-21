package com.fellinger.yeasman.game.box2d

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.fellinger.yeasman.game.utils.Once
import com.fellinger.yeasman.game.utils.Size
import com.fellinger.yeasman.game.utils.SizeConverter
import com.fellinger.yeasman.game.utils.advanced.AdvancedGroup
import com.fellinger.yeasman.game.utils.advanced.AdvancedStage
import com.fellinger.yeasman.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class AbstractBody {
    abstract val worldUtil    : WorldUtil
    abstract var id           : BodyId
    abstract val name         : String
    abstract val bodyDef      : BodyDef
    abstract val fixtureDef   : FixtureDef
    abstract val collisionList: MutableList<BodyId>

    open val actor: AdvancedGroup? = null

    lateinit var stageUI             : AdvancedStage private set
    lateinit var sizeConverterUIToBox: SizeConverter private set
    lateinit var sizeConverterBoxToUI: SizeConverter private set

    val size          = Size()
    val position      = Vector2()
    val coroutine     = CoroutineScope(Dispatchers.Default)

    val scale  by lazy { sizeConverterUIToBox.getScale(size.width) }
    val center by lazy { worldUtil.bodyEditor.getOrigin(name, scale) }
    val body   by lazy { worldUtil.world.createBody(bodyDef).apply { userData = this@AbstractBody } }

    private var beginContactBlock: (AbstractBody) -> Unit = {}
    private var endContactBlock: (AbstractBody) -> Unit   = {}
    private var renderBlock: (Float) -> Unit              = {}

    private val destroyOnce = Once()



    open fun render(deltaTime: Float) {
        renderActor()
        renderBlock(deltaTime)
    }

    open fun destroy() {
        cancelCoroutinesAll(coroutine)
        actor?.dispose()
        collisionList.clear()

        with(WorldUtil) {
            worldUtil.world.destroyBody(body)
            worldUtil.renderList.remove(this@AbstractBody)
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
            stageUI.addActor(this)
            setBounds(position.x, position.y, size.width, size.height)
        }
    }

    private fun renderActor() {
        actor?.apply {
            sizeConverterBoxToUI.apply {
                x = getSizeX(body.position.x - center.x)
                y = getSizeY(body.position.y - center.y)
                setOrigin(getSizeX(center.x), getSizeY(center.y))
            }
            rotation = Math.toDegrees(body.angle.toDouble()).toFloat()
        }
    }

    private fun createBody() {
        worldUtil.bodyEditor.attachFixture(body!!, name, fixtureDef, scale)
        worldUtil.renderList.add(this)

        actor?.let { addActor() }
    }



    fun initialize(
        stageUI: AdvancedStage,
        sizeConverterUIToBox: SizeConverter,
        sizeConverterBoxToUI: SizeConverter,
        position: Vector2,
        size: Size,
    ) {
        this.stageUI = stageUI
        this.sizeConverterUIToBox = sizeConverterUIToBox
        this.sizeConverterBoxToUI = sizeConverterBoxToUI
        this.position.set(position)
        this.size.set(size)

        with(sizeConverterUIToBox) {
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
                worldUtil.destroyList.add(this@AbstractBody)
            }
        }
    }

    fun setBeginContactBlock(block: (AbstractBody) -> Unit = {}) {
        beginContactBlock = block
    }

    fun setEndContactBlock(block: (AbstractBody) -> Unit = {}) {
        endContactBlock = block
    }

    fun setRenderBlock(block: (Float) -> Unit = {}) {
        renderBlock = block
    }

}