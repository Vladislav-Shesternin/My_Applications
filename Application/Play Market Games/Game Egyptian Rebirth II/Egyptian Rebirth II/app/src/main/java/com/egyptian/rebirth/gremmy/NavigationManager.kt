package com.egyptian.rebirth.gremmy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.Viewport
import com.egyptian.rebirth.dop_papka.gamed

object NavigationManager {

    private val backStack = mutableListOf<Screen>()
    var key: Int? = null
        private set

    val Texture.region: TextureRegion get() = TextureRegion(this)

    val Viewport.zeroScreenVector: Vector2 get() = project(Vector2(0f, 0f))



    fun disposeAll(vararg disposable: Disposable) {
        disposable.forEach { it.dispose() }
    }

    fun InputMultiplexer.addProcessors(vararg processor: InputProcessor) {
        processor.onEach { addProcessor(it) }
    }


    fun navigate(to: Screen, from: Screen? = null, key: Int? = null) {
        Gdx.app.postRunnable {
            NavigationManager.key = key

            gamed.screen = to
            from?.let { backStack.add(it) }
            if (backStack.contains(to)) backStack.remove(to)
        }
    }

    fun back(key: Int? = null) {
        Gdx.app.postRunnable {
            NavigationManager.key = key

            if (backStack.isEmpty()) exit()
            else gamed.screen = backStack.removeLast()
        }
    }

    fun exit() {
        Gdx.app.postRunnable {
            backStack.clear()
            gamed.dispose()
            Gdx.app.exit()
        }
    }

}