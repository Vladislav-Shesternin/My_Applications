package com.phara.ohegy.ptgame.game.manager

import com.badlogic.gdx.Gdx
import com.phara.ohegy.ptgame.game.LibGDXGame
import com.phara.ohegy.ptgame.game.screens.LoaderScreen
import com.phara.ohegy.ptgame.game.screens.MenuScreen
import com.phara.ohegy.ptgame.game.screens.ResultScreen
import com.phara.ohegy.ptgame.game.screens.RulesScreen
import com.phara.ohegy.ptgame.game.screens.SettingsScreen
import com.phara.ohegy.ptgame.game.screens.level.Level_1_Screen
import com.phara.ohegy.ptgame.game.screens.level.Level_2_Screen
import com.phara.ohegy.ptgame.game.utils.advanced.AdvancedScreen
import com.phara.ohegy.ptgame.game.utils.runGDX

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
        LoaderScreen  ::class.java.name -> LoaderScreen(game)
        MenuScreen    ::class.java.name -> MenuScreen(game)
        RulesScreen   ::class.java.name -> RulesScreen(game)
        SettingsScreen::class.java.name -> SettingsScreen(game)
        ResultScreen  ::class.java.name -> ResultScreen(game)

        // Level
        Level_1_Screen::class.java.name -> Level_1_Screen(game)
        Level_2_Screen::class.java.name -> Level_2_Screen(game)

        else -> MenuScreen(game)
    }

}