package com.guardians.galaxyguano.game.manager

import com.badlogic.gdx.Gdx
import com.guardians.galaxyguano.game.LibGDXGame
import com.guardians.galaxyguano.game.screens.GalaxyGameScreen
import com.guardians.galaxyguano.game.screens.GalaxyLoaderScreen
import com.guardians.galaxyguano.game.utils.advanced.AdvancedScreen
import com.guardians.galaxyguano.game.utils.runGDX

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
        GalaxyLoaderScreen::class.java.name -> GalaxyLoaderScreen(game)
        GalaxyGameScreen  ::class.java.name -> GalaxyGameScreen(game)

        else                                -> GalaxyLoaderScreen(game)
    }

}


