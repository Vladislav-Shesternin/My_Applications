package com.hlperki.pesgllra.game.manager

import com.badlogic.gdx.Gdx
import com.hlperki.pesgllra.game.LibGDXGame
import com.hlperki.pesgllra.game.screens.HappyMenuScreen
import com.hlperki.pesgllra.game.screens.HappyPrivacyScreen
import com.hlperki.pesgllra.game.screens.HappyQuizScreen
import com.hlperki.pesgllra.game.screens.HappySplashScreen
import com.hlperki.pesgllra.game.utils.advanced.AdvancedScreen
import com.hlperki.pesgllra.game.utils.runGDX

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
        HappySplashScreen::class.java.name -> HappySplashScreen(game)
        HappyMenuScreen  ::class.java.name -> HappyMenuScreen(game)
        HappyQuizScreen  ::class.java.name -> HappyQuizScreen(game)
        HappyPrivacyScreen  ::class.java.name -> HappyPrivacyScreen(game)

        else -> HappyMenuScreen(game)
    }

}