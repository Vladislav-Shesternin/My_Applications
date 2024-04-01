package com.destroyer.buildings.game.manager

import com.badlogic.gdx.Gdx
import com.destroyer.buildings.game.LibGDXGame
import com.destroyer.buildings.game.screens.DestroyGameScreen_1
import com.destroyer.buildings.game.screens.DestroyGameScreen_2
import com.destroyer.buildings.game.screens.DestroyGameScreen_3
import com.destroyer.buildings.game.screens.DestroyLoaderScreen
import com.destroyer.buildings.game.screens.DestroyMenuScreen
import com.destroyer.buildings.game.utils.advanced.AdvancedScreen
import com.destroyer.buildings.game.utils.runGDX

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
        DestroyLoaderScreen::class.java.name -> DestroyLoaderScreen(game)
        DestroyMenuScreen  ::class.java.name -> DestroyMenuScreen(game)

        DestroyGameScreen_1::class.java.name -> DestroyGameScreen_1(game)
        DestroyGameScreen_2::class.java.name -> DestroyGameScreen_2(game)
        DestroyGameScreen_3::class.java.name -> DestroyGameScreen_3(game)

        else -> DestroyMenuScreen(game)
    }

}


