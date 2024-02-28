package com.country.birds.game.manager

import com.badlogic.gdx.Gdx
import com.country.birds.game.LibGDXGame
import com.country.birds.game.screens.CountryScreen
import com.country.birds.game.screens.SelectBirdScreen
import com.country.birds.game.screens.StartScreen
import com.country.birds.game.utils.advanced.AdvancedScreen
import com.country.birds.game.utils.runGDX

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
        StartScreen     ::class.java.name -> StartScreen(game)
        SelectBirdScreen::class.java.name -> SelectBirdScreen(game)
        CountryScreen   ::class.java.name -> CountryScreen(game)

        else -> SelectBirdScreen(game)
    }

}


