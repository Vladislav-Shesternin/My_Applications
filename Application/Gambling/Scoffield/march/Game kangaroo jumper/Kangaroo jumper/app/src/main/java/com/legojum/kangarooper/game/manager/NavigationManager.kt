package com.legojum.kangarooper.game.manager

import com.badlogic.gdx.Gdx
import com.legojum.kangarooper.game.LibGDXGame
import com.legojum.kangarooper.game.screens.KangarooGameScreen
import com.legojum.kangarooper.game.screens.KangarooLoaderScreen
import com.legojum.kangarooper.game.screens.KangarooMenuScreen
import com.legojum.kangarooper.game.utils.advanced.AdvancedScreen
import com.legojum.kangarooper.game.utils.runGDX

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
        KangarooLoaderScreen::class.java.name -> KangarooLoaderScreen(game)
        KangarooMenuScreen  ::class.java.name -> KangarooMenuScreen(game)
        KangarooGameScreen  ::class.java.name -> KangarooGameScreen(game)

        else -> KangarooMenuScreen(game)
    }

}


