package com.balstar.linecomedian.game.manager

import com.badlogic.gdx.Gdx
import com.balstar.linecomedian.game.LibGDXGame
import com.balstar.linecomedian.game.screens.PinkMenuScreen
import com.balstar.linecomedian.game.screens.PinkRulesScreen
import com.balstar.linecomedian.game.screens.PinkLoaderScreen
import com.balstar.linecomedian.game.screens.PinkSettingsScreen
import com.balstar.linecomedian.game.screens.PinkYrowniScreen
import com.balstar.linecomedian.game.screens.ResultScreen
import com.balstar.linecomedian.game.screens.levels._1_Screen
import com.balstar.linecomedian.game.screens.levels._2_Screen
import com.balstar.linecomedian.game.screens.levels._3_Screen
import com.balstar.linecomedian.game.screens.levels._4_Screen
import com.balstar.linecomedian.game.utils.advanced.AdvancedScreen
import com.balstar.linecomedian.game.utils.runGDX

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
        PinkLoaderScreen   ::class.java.name -> PinkLoaderScreen(game)
        PinkMenuScreen     ::class.java.name -> PinkMenuScreen(game)
        PinkRulesScreen    ::class.java.name -> PinkRulesScreen(game)
        PinkSettingsScreen ::class.java.name -> PinkSettingsScreen(game)
        PinkYrowniScreen   ::class.java.name -> PinkYrowniScreen(game)
        ResultScreen       ::class.java.name -> ResultScreen(game)

        // ilevel
        _1_Screen::class.java.name -> _1_Screen(game)
        _2_Screen::class.java.name -> _2_Screen(game)
        _3_Screen::class.java.name -> _3_Screen(game)
        _4_Screen::class.java.name -> _4_Screen(game)

        else -> PinkMenuScreen(game)
    }

}


