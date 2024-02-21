package com.sca.rab.que.stgame.game.manager

import com.badlogic.gdx.Gdx
import com.sca.rab.que.stgame.game.LibGDXGame
import com.sca.rab.que.stgame.game.screens.LevelScreen
import com.sca.rab.que.stgame.game.screens.LoadScreen
import com.sca.rab.que.stgame.game.screens.MenuScreen
import com.sca.rab.que.stgame.game.screens.PuzzleScreen
import com.sca.rab.que.stgame.game.screens.ResultScreen
import com.sca.rab.que.stgame.game.screens.RulesScreen
import com.sca.rab.que.stgame.game.screens.SettingsScreen
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

    fun clearBackStack() {
        backStack.clear()
    }

    fun isBackStackEmpty() = backStack.isEmpty()

    private fun getScreenByName(name: String): AdvancedScreen = when(name) {
        LoadScreen     ::class.java.name -> LoadScreen(game)
        MenuScreen     ::class.java.name -> MenuScreen(game)
        RulesScreen    ::class.java.name -> RulesScreen(game)
        SettingsScreen ::class.java.name -> SettingsScreen(game)
        LevelScreen    ::class.java.name -> LevelScreen(game)
        PuzzleScreen   ::class.java.name -> PuzzleScreen(game)
        ResultScreen   ::class.java.name -> ResultScreen(game)

        else -> MenuScreen(game)
    }

}