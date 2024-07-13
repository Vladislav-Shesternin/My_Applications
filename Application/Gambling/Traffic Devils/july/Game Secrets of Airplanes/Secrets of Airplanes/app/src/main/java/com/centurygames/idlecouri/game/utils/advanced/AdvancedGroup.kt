package com.centurygames.idlecouri.game.utils.advanced

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Disposable
import com.centurygames.idlecouri.game.utils.SizeStandardizer
import com.centurygames.idlecouri.game.utils.disposeAll
import com.centurygames.idlecouri.util.OneTime
import com.centurygames.idlecouri.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AdvancedGroup : WidgetGroup(), Disposable {
    abstract val screen: AdvancedScreen

    open var standartW: Float = 1f

    var coroutine: CoroutineScope? = CoroutineScope(Dispatchers.Default)
        private set
    var isDisposed = false

    private val standardizer = SizeStandardizer()

    val Vector2.toStandart get() = standardizer.scope { toStandart }
    val Float.toStandart   get() = standardizer.scope { toStandart }

    val preDrawArray  = Array<Static.Drawer>()
    val postDrawArray = Array<Static.Drawer>()
    val disposableSet = mutableSetOf<Disposable>()

    private val onceInit = OneTime()

    abstract fun addActorsOnGroup()

    override fun draw(batch: Batch?, parentAlpha: Float) {
        preDrawArray.forEach { it.draw(parentAlpha * this@AdvancedGroup.color.a) }
        super.draw(batch, parentAlpha)
        postDrawArray.forEach { it.draw(parentAlpha * this@AdvancedGroup.color.a) }
    }


    override fun sizeChanged() {
        standardizer.standardize(standartW, width)
        onceInit.use { addActorsOnGroup() }
    }

    override fun dispose() {
        if (isDisposed.not()) {
            preDrawArray.clear()
            postDrawArray.clear()

            disposableSet.disposeAll()
            disposableSet.clear()

            disposeChildren()

            cancelCoroutinesAll(coroutine)
            coroutine = null

            isDisposed = true
        }
    }

    fun disposeChildren() {
        children.onEach { actor -> if (actor is Disposable) actor.dispose() }
        clearChildren()
    }

    override fun removeActorAt(index: Int, unfocus: Boolean): Actor {
        children[index].also { if(it is Disposable) it.dispose() }
        return super.removeActorAt(index, unfocus)
    }

    fun addAndFillActor(actor: Actor) {
        addActor(actor)
        actor.setSize(width, height)
    }

    fun addAndFillActors(actors: List<Actor>) {
        actors.forEach { addActor(it.also { a -> a.setSize(width, height) }) }
    }

    fun addAndFillActors(vararg actors: Actor) {
        actors.forEach { addActor(it.also { a -> a.setSize(width, height) }) }
    }

    fun addActors(vararg actors: Actor) {
        actors.forEach { addActor(it) }
    }

    protected fun Actor.setBoundsStandart(x: Float, y: Float, width: Float, height: Float) {
        setBounds(x.toStandart, y.toStandart, width.toStandart, height.toStandart)
    }

    protected fun Actor.setBoundsStandart(position: Vector2, size: Vector2) {
        setBoundsStandart(position.x, position.y, size.x, size.y)
    }

    object Static {
        fun interface Drawer {
            fun draw(alpha: Float)
        }
    }

}