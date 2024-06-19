package com.hepagame.game.manager

import com.badlogic.gdx.Gdx
import com.hepagame.game.LibGDXGame
import com.hepagame.game.screens.*
import com.hepagame.game.utils.advanced.AdvancedScreen
import com.hepagame.game.utils.runGDX

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
        SettingsScreen::class.java.name -> SettingsScreen(game)
        RulesScreen::class.java.name -> RulesScreen(game)
        GameScreen::class.java.name -> GameScreen(game)

        DianisScreen::class.java.name -> DianisScreen(game)

        else -> MenuScreen(game)
    }

}