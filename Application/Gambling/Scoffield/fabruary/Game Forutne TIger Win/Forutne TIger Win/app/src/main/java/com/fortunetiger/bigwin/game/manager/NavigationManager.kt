package com.fortunetiger.bigwin.game.manager

import com.badlogic.gdx.Gdx
import com.fortunetiger.bigwin.game.LibGDXGame
import com.fortunetiger.bigwin.game.screens.FTWMenuScreen
import com.fortunetiger.bigwin.game.screens.FTWLoaderScreen
import com.fortunetiger.bigwin.game.screens.FTWRulesScreen
import com.fortunetiger.bigwin.game.screens.FTWSettingsScreen
import com.fortunetiger.bigwin.game.utils.advanced.AdvancedScreen
import com.fortunetiger.bigwin.game.utils.runGDX

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

    private fun getScreenByName(name: String): AdvancedScreen = when (name) {
        FTWLoaderScreen::class.java.name   -> FTWLoaderScreen(game)
        FTWMenuScreen::class.java.name     -> FTWMenuScreen(game)
        FTWRulesScreen::class.java.name    -> FTWRulesScreen(game)
        FTWSettingsScreen::class.java.name -> FTWSettingsScreen(game)

        else -> FTWLoaderScreen(game)
    }

}