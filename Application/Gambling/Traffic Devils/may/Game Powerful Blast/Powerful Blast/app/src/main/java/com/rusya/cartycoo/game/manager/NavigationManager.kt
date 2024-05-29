package com.rusya.cartycoo.game.manager

import com.badlogic.gdx.Gdx
import com.rusya.cartycoo.game.PoromGame
import com.rusya.cartycoo.game.screens.*
import com.rusya.cartycoo.game.utils.advanced.AdvancedScreen
import com.rusya.cartycoo.game.utils.runGDX

class NavigationManager(val game: PoromGame) {

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
        LodrinkingScreen  ::class.java.name -> LodrinkingScreen(game)
        MedinaOleylaScreen ::class.java.name -> MedinaOleylaScreen(game)
        EsteticaScreen  ::class.java.name -> EsteticaScreen(game)
        SetsScreen::class.java.name -> SetsScreen(game)
        SviterScreen ::class.java.name -> SviterScreen(game)

        else -> MedinaOleylaScreen(game)
    }

}