package com.radarada.avia.game.manager

import com.badlogic.gdx.Gdx
import com.radarada.avia.game.LibGDXGame
import com.radarada.avia.game.screens.GameScreen
import com.radarada.avia.game.screens.MenuScreen
import com.radarada.avia.game.screens.RulesScreen
import com.radarada.avia.game.screens.SettingsScreen
import com.radarada.avia.game.screens.ShopScreen
import com.radarada.avia.game.screens.SplashcScreen
import com.radarada.avia.game.utils.advanced.AdvancedScreen
import com.radarada.avia.game.utils.runGDX

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
        SplashcScreen ::class.java.name -> SplashcScreen(game)
        MenuScreen    ::class.java.name -> MenuScreen(game)
        RulesScreen   ::class.java.name -> RulesScreen(game)
        SettingsScreen::class.java.name -> SettingsScreen(game)
        ShopScreen    ::class.java.name -> ShopScreen(game)
        GameScreen    ::class.java.name -> GameScreen(game)

        else -> MenuScreen(game)
    }

}