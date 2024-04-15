package qbl.bisriymyach.QuickBall.hotvils

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import qbl.bisriymyach.QuickBall.Ebufren
import qbl.bisriymyach.QuickBall.fastergan.hshshshJ
import qbl.bisriymyach.QuickBall.sudams.Destroyable
import qbl.bisriymyach.QuickBall.sudams.Oi_oi_uoi
import qbl.bisriymyach.QuickBall.sudams.destroyAll
import qbl.bisriymyach.QuickBall.tidams.cancelCoroutinesAll
import qbl.bisriymyach.QuickBall.tidams.log
import qbl.bisriymyach.QuickBall.tidams.zizi

abstract class Corona : Destroyable {
    abstract val screenBox2d: Ebufren
    abstract val standartW: Float

    private val _bodyList = mutableSetOf<Bibash>()


    var coroutine: CoroutineScope? = null
        private set

    val position = Vector2()
    val size = Vector2()


    open fun mackro(x: Float, y: Float, w: Float, h: Float) {
        position.set(x, y)
        size.set(w, h)

        coroutine = CoroutineScope(Dispatchers.Default)
        standardizer.standardize(standartW, w)
    }

    private val _actorList = mutableSetOf<Oi_oi_uoi>()
    val disposableSet = mutableSetOf<Disposable>()
    val destroyableSet = mutableSetOf<Destroyable>()


    private val standardizer = zizi()
    val Vector2.toStandartBG get() = standardizer.scope { toStandart }
    override fun dodo() {
        log("destroy: ${this::class.java.name.substringAfterLast('.')}")
        cancelCoroutinesAll(coroutine)
        disposableSet.hshshshJ()
        destroyableSet.destroyAll()

        _bodyList.destroyAll()
        _actorList.hshshshJ()
    }

    fun createBody(body: Bibash, pos: Vector2, size: Vector2) {
        body.create(position.cpy().add(pos.toStandartBG), size.toStandartBG)
        _bodyList.add(body)
        body.ultima?.let { _actorList.add(it) }
    }

    fun createBody(body: Bibash, x: Float, y: Float, w: Float, h: Float) {
        createBody(body, Vector2(x, y), Vector2(w, h))
    }


}