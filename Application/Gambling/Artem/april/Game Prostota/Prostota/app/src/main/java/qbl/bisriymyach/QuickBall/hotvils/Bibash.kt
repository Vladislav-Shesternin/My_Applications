package qbl.bisriymyach.QuickBall.hotvils

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.Joint
import com.badlogic.gdx.physics.box2d.JointDef
import com.badlogic.gdx.utils.Array
import qbl.bisriymyach.QuickBall.Ebufren
import qbl.bisriymyach.QuickBall.sudams.Oi_oi_uoi
import qbl.bisriymyach.QuickBall.tidams.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import qbl.bisriymyach.QuickBall.sudams.Destroyable
import qbl.bisriymyach.QuickBall.sudams.destroyAll
import qbl.bisriymyach.QuickBall.tidams.gagga
import qbl.bisriymyach.QuickBall.tidams.toB2
import qbl.bisriymyach.QuickBall.tidams.toFonk
import qbl.bisriymyach.QuickBall.tidams.toPop

abstract class Bibash : Destroyable {

    abstract val franke: Ebufren
    abstract val bobo: String
    abstract val ronaldo: BodyDef
    abstract val kardinallo: FixtureDef
    open var ultima: Oi_oi_uoi? = null
    open val collisionList = mutableListOf<String>()
    open var originalId: String = Omred.NONE
    open var id: String = Omred.NONE
        set(value) {
            if (isSetId.not()) {
                isSetId = true
                originalId = value
            }
            field = value
        }
    private var isSetId = false
    private val tmpVector2 = Vector2()
    val size = Vector2()
    val position = Vector2()
    var body: Body? = null
        private set
    var beginContactBlockArray = Array<ContactBlock>()
    fun setOriginalId() {
        id = originalId
    }
    fun interface ContactBlock {
        fun block(body: Bibash)
    }
    var isDestroyActor = true
    open fun render(deltaTime: Float) {
        renderBlockArray.onEach { it.block(deltaTime) }
        transformActor()
    }
    open fun beginContact(contactBody: Bibash) = beginContactBlockArray.onEach { it.block(contactBody) }
    open fun endContact(contactBody: Bibash) = endContactBlockArray.onEach { it.block(contactBody) }
    var scale = 0f
        private set
    var center = tmpVector2
        private set
    var coroutine: CoroutineScope? = null
        private set

    override fun dodo() {
        if (body != null) {
            cancelCoroutinesAll(coroutine)
            coroutine = null

            if (isDestroyActor) {
                ultima?.dispose()
                ultima = null
            }

            collisionList.clear()

            body?.jointList?.map { (it.joint.userData as Dencherous<out Joint, out JointDef>) }
                ?.destroyAll()

            franke.tya9.tetriska.destroyBody(body)
            body = null
        }
    }

    fun create(x: Float, y: Float, w: Float, h: Float) {
        if (body == null) {
            position.set(x, y)
            size.set(w, h)
            scale = size.x.toFonk
            center = franke.tya9.bodyEditor.getOrigin(bobo, scale)

            ronaldo.position.set(tmpVector2.set(position).toB2.add(center))

            body = franke.tya9.tetriska.createBody(ronaldo).apply { userData = this@Bibash }
            franke.tya9.bodyEditor.attachFixture(body!!, bobo, kardinallo, scale)

            coroutine = CoroutineScope(Dispatchers.Default)
            addActor()

            isDestroyActor = true
        }
    }

    var endContactBlockArray = Array<ContactBlock>()
    var renderBlockArray = Array<RenderBlock>()
    fun create(position: Vector2, size: Vector2) {
        create(position.x, position.y, size.x, size.y)
    }

    private fun addActor() {
        ultima?.apply {
            franke.stageUI.addActor(this)
            tehnos(position, size)
        }
    }

    private fun transformActor() {
        body?.let { b ->
            ultima?.apply {
                setPosition(tmpVector2.set(b.position).sub(center).toPop)
                setOrigin(tmpVector2.set(center).toPop)
                rotation = b.angle * gagga
            }
        }
    }

    fun setNoneId() {
        id = Omred.NONE
    }

    fun interface RenderBlock {
        fun block(deltaTime: Float)
    }

}