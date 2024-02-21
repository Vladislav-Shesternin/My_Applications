package com.fellinger.yeasman.game.manager

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.fellinger.yeasman.game.game
import com.fellinger.yeasman.game.utils.runGDX

object NavigationManager {

    private val backStack = mutableListOf<Screen>()
    var key: Int? = null
        private set

    fun navigate(to: Screen, from: Screen? = null, key: Int? = null) {
        runGDX {
            NavigationManager.key = key

            game.screen = to
            from?.let {
                if (backStack.contains(it)) backStack.remove(it)
                backStack.add(it)
            }
            if (backStack.contains(to)) backStack.remove(to)
        }
    }

    fun back(key: Int? = null) {
        runGDX {
            NavigationManager.key = key

            if (backStack.isEmpty()) exit()
            else game.screen = backStack.removeLast()
        }
    }

    fun exit() {
        runGDX {
            game.dispose()
            backStack.clear()
            Gdx.app.exit()
        }
    }

}