package com.god.sof.olym.pus.game.manager

import com.badlogic.gdx.Gdx
import com.god.sof.olym.pus.game.LibGDXGame
import com.god.sof.olym.pus.game.screens.OlyFinishScreen
import com.god.sof.olym.pus.game.screens.OlyGameScreen
import com.god.sof.olym.pus.game.screens.OlyLevelScreen
import com.god.sof.olym.pus.game.screens.OlyLoadingScreen
import com.god.sof.olym.pus.game.screens.OlyMenuScreen
import com.god.sof.olym.pus.game.screens.OlyRulesScreen
import com.god.sof.olym.pus.game.screens.OlySettingsScreen
import com.god.sof.olym.pus.game.utils.advanced.AdvancedScreen
import com.god.sof.olym.pus.game.utils.runGDX

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
        OlyLoadingScreen ::class.java.name -> OlyLoadingScreen(game)
        OlyMenuScreen    ::class.java.name -> OlyMenuScreen(game)
        OlyLevelScreen   ::class.java.name -> OlyLevelScreen(game)
        OlyRulesScreen   ::class.java.name -> OlyRulesScreen(game)
        OlySettingsScreen::class.java.name -> OlySettingsScreen(game)
        OlyGameScreen    ::class.java.name -> OlyGameScreen(game)
        OlyFinishScreen  ::class.java.name -> OlyFinishScreen(game)

        else -> OlyLoadingScreen(game)
    }

}