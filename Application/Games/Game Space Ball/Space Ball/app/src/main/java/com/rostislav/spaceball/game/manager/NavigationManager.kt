package com.rostislav.spaceball.game.manager

import com.badlogic.gdx.Gdx
import com.rostislav.spaceball.game.GdxGame
import com.rostislav.spaceball.game.screens.AbstractGameScreen
import com.rostislav.spaceball.game.screens.LoaderScreen
import com.rostislav.spaceball.game.screens.MenuScreen
import com.rostislav.spaceball.game.screens.WinScreen
import com.rostislav.spaceball.game.utils.advanced.AdvancedScreen
import com.rostislav.spaceball.game.utils.runGDX

class NavigationManager(val game: GdxGame) {

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
        MenuScreen  ::class.java.name -> MenuScreen(game)
        AbstractGameScreen::class.java.name -> AbstractGameScreen(game)
        WinScreen::class.java.name -> WinScreen(game)

        else -> MenuScreen(game)
    }

}