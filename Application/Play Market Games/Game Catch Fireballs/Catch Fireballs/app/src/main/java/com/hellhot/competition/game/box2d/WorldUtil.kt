package com.hellhot.competition.game.box2d

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Disposable
import com.hellhot.competition.cancelCoroutinesAll
import com.hellhot.competition.game.box2d.bodies.AbstractBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object WorldUtil: Disposable {

    const val GRAVITY_X = 0f
    const val GRAVITY_Y = -9.8f
    private const val TIME_STEP: Float = 1f / 60f

    private var accumulatorTime = 0f
    private val coroutineMain   = CoroutineScope(Dispatchers.Main)

    val world         by lazy { World(Vector2(GRAVITY_X, GRAVITY_Y), true) }
    val debugRenderer by lazy { Box2DDebugRenderer(true, true, false, true, true, true) }
    val bodyEditor    by lazy { BodyEditorLoader(Gdx.files.internal("physics/PhysicsData")) }

    val renderList  = mutableListOf<AbstractBody>()
    val destroyList = mutableListOf<AbstractBody>()



    init {
        world.setContactFilter(WorldContactFilter)
        world.setContactListener(WorldContactListener)
    }

    override fun dispose() {
        destroyList.destroyAll()
        cancelCoroutinesAll(coroutineMain)
        world.dispose()
    }



    fun update(deltaTime: Float) {
        accumulatorTime += deltaTime

        while (accumulatorTime >= TIME_STEP) {
            world.step(TIME_STEP, 6, 2)
            accumulatorTime -= TIME_STEP
        }

        renderList.onEach { it.render(deltaTime) }
        destroyList.onEach { it.destroy() }.clear()
    }

    fun debug(matrix4: Matrix4) {
       // debugRenderer.render(world, matrix4)
    }

}