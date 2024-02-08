package com.sca.rab.que.stgame.game.manager

import com.badlogic.gdx.Gdx
import com.sca.rab.que.stgame.game.LibGDXGame
import com.sca.rab.que.stgame.game.screens.LoaLevelScreen
import com.sca.rab.que.stgame.game.screens.LoaMenuScreen
import com.sca.rab.que.stgame.game.screens.LoaLoadScreen
import com.sca.rab.que.stgame.game.screens.LoaPuzzleScreen
import com.sca.rab.que.stgame.game.screens.LoaRulesScreen
import com.sca.rab.que.stgame.game.screens.LoaSettingsScreen
import com.sca.rab.que.stgame.game.utils.advanced.AdvancedScreen
import com.sca.rab.que.stgame.game.utils.runGDX

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
        LoaLoadScreen     ::class.java.name -> LoaLoadScreen(game)
        LoaMenuScreen     ::class.java.name -> LoaMenuScreen(game)
        LoaRulesScreen    ::class.java.name -> LoaRulesScreen(game)
        LoaSettingsScreen ::class.java.name -> LoaSettingsScreen(game)
        LoaLevelScreen    ::class.java.name -> LoaLevelScreen(game)
        LoaPuzzleScreen   ::class.java.name -> LoaPuzzleScreen(game)

        else -> LoaMenuScreen(game)
    }

}