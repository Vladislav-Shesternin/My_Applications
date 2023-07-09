package com.uchimenkoe.financecounter.game.manager

import com.badlogic.gdx.Gdx
import com.uchimenkoe.financecounter.game.game
import com.uchimenkoe.financecounter.game.utils.advanced.AdvancedScreen
import com.uchimenkoe.financecounter.game.utils.runGDX

object NavigationManager {

    private val backStack = mutableListOf<AdvancedScreen>()
    var key: Int? = null
        private set

    fun navigate(to: AdvancedScreen, from: AdvancedScreen? = null, key: Int? = null) {
        runGDX {
            NavigationManager.key = key

            game.screen = to
            from?.let { f ->
                backStack.filter { it.name == f.name }.onEach { backStack.remove(it) }
                backStack.add(f)
            }
            backStack.filter { it.name == to.name }.onEach { backStack.remove(it) }
        }
    }

    fun back(key: Int? = null) {
        runGDX {
            NavigationManager.key = key

            if (backStack.isEmpty()) exit()
            else game.screen = backStack.removeLast().javaClass.newInstance()
        }
    }

    fun exit() {
        runGDX {
            backStack.clear()
            game.dispose()
            Gdx.app.exit()
        }
    }

}