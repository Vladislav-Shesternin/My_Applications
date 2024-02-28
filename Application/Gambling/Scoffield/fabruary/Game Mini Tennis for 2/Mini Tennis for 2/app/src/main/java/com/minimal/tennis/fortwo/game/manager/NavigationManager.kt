package com.minimal.tennis.fortwo.game.manager

import com.badlogic.gdx.Gdx
import com.minimal.tennis.fortwo.game.LibGDXGame
import com.minimal.tennis.fortwo.game.screens.LoaderScreen
import com.minimal.tennis.fortwo.game.screens.TennisScreen
import com.minimal.tennis.fortwo.game.utils.advanced.AdvancedScreen
import com.minimal.tennis.fortwo.game.utils.runGDX

class NavigationManager(val game: LibGDXGame) {

    private val backStack = mutableListOf<String>()
    var key: Int? = null
        private set

    fun navigate(toScreenName: String, fromScreenName: String? = null, key: Int? = null) = runGDX {
        this.key = key

        game.updateScreen(getScreenByName(toScreenName))
        backStack.filter { name -> name == toScreenName }.onEach { name -> backStack.remove(name) }
        fromScreenName?.let { fromName ->
            backStack.filter { name -> name == fromName }.onEach { name -> backStack.remove(name) }
            backStack.add(fromName)
        }
    }

    fun back(key: Int? = null) = runGDX {
        this.key = key

        if (isBackStackEmpty()) exit() else game.updateScreen(getScreenByName(backStack.removeLast()))
    }


    fun exit() = runGDX { Gdx.app.exit() }


    fun isBackStackEmpty() = backStack.isEmpty()

    private fun getScreenByName(name: String): AdvancedScreen = when(name) {
        LoaderScreen::class.java.name -> LoaderScreen(game)
        TennisScreen::class.java.name -> TennisScreen(game)

        else -> LoaderScreen(game)
    }

}


