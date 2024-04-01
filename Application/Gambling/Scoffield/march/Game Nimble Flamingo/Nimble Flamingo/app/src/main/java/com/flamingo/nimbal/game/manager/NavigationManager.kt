package com.flamingo.nimbal.game.manager

import com.badlogic.gdx.Gdx
import com.flamingo.nimbal.game.LibGDXGame
import com.flamingo.nimbal.game.screens.FlamingoGameScreen
import com.flamingo.nimbal.game.screens.FlamingoLoaderScreen
import com.flamingo.nimbal.game.utils.advanced.AdvancedScreen
import com.flamingo.nimbal.game.utils.runGDX

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
        FlamingoLoaderScreen::class.java.name -> FlamingoLoaderScreen(game)
        FlamingoGameScreen  ::class.java.name -> FlamingoGameScreen(game)

        else -> FlamingoGameScreen(game)
    }

}


