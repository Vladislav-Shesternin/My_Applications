package qbl.bisriymyach.QuickBall.sudams

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Disposable
import qbl.bisriymyach.QuickBall.tidams.zizi
import qbl.bisriymyach.QuickBall.hotvils.hjg
import qbl.bisriymyach.QuickBall.fastergan.hshshshJ
import qbl.bisriymyach.QuickBall.hotvils.vvavava
import qbl.bisriymyach.QuickBall.tidams.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import qbl.bisriymyach.QuickBall.fastergan.suchka

abstract class Oi_oi_uoi : WidgetGroup(), Disposable {
    abstract val screen: suchka

    open var standartW: Float = 1f

    var coroutine: CoroutineScope? = CoroutineScope(Dispatchers.Default)
        private set
    var isDisposed = false
        private set

    private val standardizer = zizi()

    val preDrawArray = Array<Drawer>()
    val postDrawArray = Array<Drawer>()
    val disposableSet = mutableSetOf<Disposable>()

    private val onceInit = vvavava()

    abstract fun addActorsOnGroup()

    override fun draw(batch: Batch?, parentAlpha: Float) {
        preDrawArray.forEach { it.draw(parentAlpha * this@Oi_oi_uoi.color.a) }
        super.draw(batch, parentAlpha)
        postDrawArray.forEach { it.draw(parentAlpha * this@Oi_oi_uoi.color.a) }
    }

    override fun sizeChanged() {
        standardizer.standardize(standartW, width)
        onceInit.once { addActorsOnGroup() }
    }

    override fun dispose() {
        if (isDisposed.not()) {
            preDrawArray.clear()
            postDrawArray.clear()

            disposableSet.hshshshJ()
            disposableSet.clear()

            children.onEach { actor -> if (actor is Disposable) actor.dispose() }
            cancelCoroutinesAll(coroutine)
            coroutine = null

            isDisposed = true
            remove()
        }
    }

    override fun removeActorAt(index: Int, unfocus: Boolean): Actor {
        children[index].also { if (it is Disposable) it.dispose() }
        return super.removeActorAt(index, unfocus)
    }

    fun addAndFillActor(actor: Actor) {
        addActor(actor)
        when (actor) {
            is Widget      -> actor.setFillParent(true)
            is WidgetGroup -> actor.setFillParent(true)
        }
    }

    fun addAndFillActors(actors: List<Actor>) {
        actors.forEach { addActor(it) }
        actors.hjg()
    }

    fun addActors(actors: List<Actor>) {
        actors.forEach { addActor(it) }
    }

    fun interface Drawer {
        fun draw(alpha: Float)
    }

}