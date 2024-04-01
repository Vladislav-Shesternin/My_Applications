package com.farello.rocketing.game.manager

import com.badlogic.gdx.Gdx
import com.farello.rocketing.game.LibGDXGame
import com.farello.rocketing.game.screens.FarelloGameScreen
import com.farello.rocketing.game.screens.FarelloLoaderScreen
import com.farello.rocketing.game.utils.advanced.AdvancedScreen
import com.farello.rocketing.game.utils.runGDX

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
        FarelloLoaderScreen::class.java.name -> FarelloLoaderScreen(game)
        FarelloGameScreen  ::class.java.name -> FarelloGameScreen(game)

        else                                 -> FarelloLoaderScreen(game)
    }

}


