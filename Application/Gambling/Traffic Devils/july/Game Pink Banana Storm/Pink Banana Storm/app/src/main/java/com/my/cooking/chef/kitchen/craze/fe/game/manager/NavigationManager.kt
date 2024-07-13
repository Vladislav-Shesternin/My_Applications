package com.my.cooking.chef.kitchen.craze.fe.game.manager

import com.badlogic.gdx.Gdx
import com.my.cooking.chef.kitchen.craze.fe.game.LibGDXGame
import com.my.cooking.chef.kitchen.craze.fe.game.screens.*
import com.my.cooking.chef.kitchen.craze.fe.game.utils.advanced.AdvancedScreen
import com.my.cooking.chef.kitchen.craze.fe.game.utils.runGDX

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
        LoadingScreen::class.java.name -> LoadingScreen(game)
        MenuScreen::class.java.name -> MenuScreen(game)
        RulesScreen::class.java.name -> RulesScreen(game)
        SettingsScreen::class.java.name -> SettingsScreen(game)
        PeregGameScreen::class.java.name -> PeregGameScreen(game)
        GameScreen::class.java.name -> GameScreen(game)
        Dilegatsia::class.java.name -> Dilegatsia(game)

        else -> MenuScreen(game)
    }

}