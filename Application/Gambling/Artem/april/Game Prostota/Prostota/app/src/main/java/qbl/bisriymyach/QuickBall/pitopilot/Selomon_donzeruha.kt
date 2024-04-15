package qbl.bisriymyach.QuickBall.pitopilot

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import qbl.bisriymyach.QuickBall.hotvils.Bibash
import qbl.bisriymyach.QuickBall.hotvils.ButafoRia
import qbl.bisriymyach.QuickBall.tidams.cancelCoroutinesAll
import qbl.bisriymyach.QuickBall.tidams.log
import qbl.bisriymyach.QuickBall.sudams.bodies
import qbl.bisriymyach.QuickBall.sudams.destroyAll

class Selomon_donzeruha : Disposable {

    companion object {
        const val tta5 = 0f


        const val tay = -15f
        private const val setet: Float = 1f / 60f

        var yatay = false
    }

    private var accumulatorTime = 0f

    val contactFilter = solistkagroupveagra()
    val contactListener = aga_ugu()
    val tetriska by lazy { World(Vector2(tta5, tay), true) }

    init {
        tetriska.setContactFilter(contactFilter)
        tetriska.setContactListener(contactListener)
    }

    override fun dispose() {
        log("WorldUtil dispose")
        cancelCoroutinesAll(coroutine)
        tetriska.bodies().map { it.userData as Bibash }.destroyAll()
        tetriska.dispose()
    }

    fun update(deltaTime: Float) {
        accumulatorTime += deltaTime

        while (accumulatorTime >= setet) {
            tetriska.step(setet, 6, 2)
            accumulatorTime -= setet
        }

        tetriska.bodies().onEach { (it.userData as Bibash).render(deltaTime) }
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)

    val debugRenderer by lazy { Box2DDebugRenderer(true, true, true, true, true, true) }
    val bodyEditor by lazy { ButafoRia(Gdx.files.internal("poligonel/PhysicsData")) }

    fun bbeh(matrix4: Matrix4) {
        if (yatay) debugRenderer.render(tetriska, matrix4)
    }

}




