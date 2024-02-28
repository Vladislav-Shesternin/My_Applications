package com.funsagon.hexagon.game.manager

import com.badlogic.gdx.Gdx
import com.funsagon.hexagon.game.LibGDXGame
import com.funsagon.hexagon.game.screens.HexagonGameScreen
import com.funsagon.hexagon.game.screens.HexagonMenuScreen
import com.funsagon.hexagon.game.screens.HexagonLoaderScreen
import com.funsagon.hexagon.game.screens.HexagonMapScreen
import com.funsagon.hexagon.game.screens.HexagonRulesScreen
import com.funsagon.hexagon.game.screens.HexagonSettingsScreen
import com.funsagon.hexagon.game.utils.advanced.AdvancedScreen
import com.funsagon.hexagon.game.utils.runGDX

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
        HexagonLoaderScreen  ::class.java.name -> HexagonLoaderScreen(game)
        HexagonMenuScreen    ::class.java.name -> HexagonMenuScreen(game)
        HexagonSettingsScreen::class.java.name -> HexagonSettingsScreen(game)
        HexagonRulesScreen   ::class.java.name -> HexagonRulesScreen(game)
        HexagonMapScreen     ::class.java.name -> HexagonMapScreen(game)
        HexagonGameScreen    ::class.java.name -> HexagonGameScreen(game)

        else -> HexagonMenuScreen(game)
    }

}


