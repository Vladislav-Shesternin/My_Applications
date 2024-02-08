package com.fortunetiger.carnival.game.manager

import com.badlogic.gdx.Gdx
import com.fortunetiger.carnival.game.LibGDXGame
import com.fortunetiger.carnival.game.screens.CarnavalGame3Screen
import com.fortunetiger.carnival.game.screens.CarnavalGame4Screen
import com.fortunetiger.carnival.game.screens.CarnavalLoaderScreen
import com.fortunetiger.carnival.game.screens.CarnavalMenuScreen
import com.fortunetiger.carnival.game.screens.CarnavalMusicScreen
import com.fortunetiger.carnival.game.utils.advanced.AdvancedScreen
import com.fortunetiger.carnival.game.utils.runGDX

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
        CarnavalLoaderScreen::class.java.name -> CarnavalLoaderScreen(game)
        CarnavalMenuScreen  ::class.java.name -> CarnavalMenuScreen(game)
        CarnavalMusicScreen ::class.java.name -> CarnavalMusicScreen(game)
        CarnavalGame3Screen ::class.java.name -> CarnavalGame3Screen(game)
        CarnavalGame4Screen ::class.java.name -> CarnavalGame4Screen(game)

        else -> CarnavalMenuScreen(game)
    }

}


