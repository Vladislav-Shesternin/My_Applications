package com.bettilt.mobile.pt.game.manager

import com.badlogic.gdx.Gdx
import com.bettilt.mobile.pt.game.LibGDXGame
import com.bettilt.mobile.pt.game.screens.GameScreen
import com.bettilt.mobile.pt.game.screens.LoaderScreen
import com.bettilt.mobile.pt.game.screens.MenuScreen
import com.bettilt.mobile.pt.game.screens.SettingsScreen
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedScreen
import com.bettilt.mobile.pt.game.utils.runGDX
import com.bettilt.mobile.pt.util.log

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
        LoaderScreen::class.java.name   -> LoaderScreen(game)
        MenuScreen::class.java.name     -> MenuScreen(game)
        SettingsScreen::class.java.name -> SettingsScreen(game)
        GameScreen::class.java.name     -> GameScreen(game)
        else                            -> MenuScreen(game)
    }

}