package com.mvgamesteam.mineta.game.manager

import com.badlogic.gdx.Gdx
import com.mvgamesteam.mineta.game.LibGDXGame
import com.mvgamesteam.mineta.game.screens.*
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedScreen
import com.mvgamesteam.mineta.game.utils.runGDX

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
        LoadingScreen::class.java.name -> LoadingScreen(game)
        MenuScreen ::class.java.name -> MenuScreen(game)
        RScreen::class.java.name -> RScreen(game)
        SettingsScreen::class.java.name -> SettingsScreen(game)
        GameScreen::class.java.name -> GameScreen(game)
        Diasaga::class.java.name -> Diasaga(game)

        else -> MenuScreen(game)
    }

}