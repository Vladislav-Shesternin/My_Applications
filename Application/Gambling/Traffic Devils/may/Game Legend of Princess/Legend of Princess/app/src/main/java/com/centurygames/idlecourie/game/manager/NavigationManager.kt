package com.centurygames.idlecourie.game.manager

import com.badlogic.gdx.Gdx
import com.centurygames.idlecourie.game.HelloMotoGame
import com.centurygames.idlecourie.game.screens.*
import com.centurygames.idlecourie.game.utils.advanced.AdvancedScreen
import com.centurygames.idlecourie.game.utils.runGDX

class NavigationManager(val game: HelloMotoGame) {

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
        Lodinga_HERA_Screen  ::class.java.name -> Lodinga_HERA_Screen(game)
        Menu_FASTERNA_Screen ::class.java.name -> Menu_FASTERNA_Screen(game)
        Settings_BUBA_Screen ::class.java.name -> Settings_BUBA_Screen(game)
        Rules_fdgDHHDH_Screen::class.java.name -> Rules_fdgDHHDH_Screen(game)
        Process_GALINTVAGEN_Screen::class.java.name -> Process_GALINTVAGEN_Screen(game)

        else -> Menu_FASTERNA_Screen(game)
    }

}