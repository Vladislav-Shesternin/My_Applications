package com.duckduckmoosedesign.cpk.game.manager

import com.duckduckmoosedesign.cpk.game.LibGDXGame
import com.duckduckmoosedesign.cpk.game.screens.*
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedScreen
import com.duckduckmoosedesign.cpk.game.utils.runGDX
import com.badlogic.gdx.Gdx

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

    private fun getScreenByName(name: String): AdvancedScreen = when (name) {
        FirsterScreen::class.java.name -> FirsterScreen(game)
        MenuScreen::class.java.name    -> MenuScreen(game)
        ManualScreen::class.java.name    -> ManualScreen(game)
        ConfigScreen::class.java.name -> ConfigScreen(game)
        PlanesScreen::class.java.name -> PlanesScreen(game)
        GameresScreen::class.java.name -> GameresScreen(game)
        Ember::class.java.name -> Ember(game)

        else -> MenuScreen(game)
    }

}