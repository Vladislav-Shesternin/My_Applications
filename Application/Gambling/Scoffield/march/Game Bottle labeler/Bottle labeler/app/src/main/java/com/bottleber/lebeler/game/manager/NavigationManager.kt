package com.bottleber.lebeler.game.manager

import com.badlogic.gdx.Gdx
import com.bottleber.lebeler.game.LibGDXGame
import com.bottleber.lebeler.game.screens.BotlerLoaderScreen
import com.bottleber.lebeler.game.screens.BotlerMenuScreen
import com.bottleber.lebeler.game.screens.bottler._10_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._11_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._12_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._1_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._2_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._3_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._4_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._5_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._6_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._7_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._8_BottlerScreen
import com.bottleber.lebeler.game.screens.bottler._9_BottlerScreen
import com.bottleber.lebeler.game.utils.advanced.AdvancedScreen
import com.bottleber.lebeler.game.utils.runGDX

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
        BotlerLoaderScreen::class.java.name -> BotlerLoaderScreen(game)
        BotlerMenuScreen  ::class.java.name -> BotlerMenuScreen(game)

        // Level
        _1_BottlerScreen::class.java.name -> _1_BottlerScreen(game)
        _2_BottlerScreen::class.java.name -> _2_BottlerScreen(game)
        _3_BottlerScreen::class.java.name -> _3_BottlerScreen(game)
        _4_BottlerScreen::class.java.name -> _4_BottlerScreen(game)
        _5_BottlerScreen::class.java.name -> _5_BottlerScreen(game)
        _6_BottlerScreen::class.java.name -> _6_BottlerScreen(game)
        _7_BottlerScreen::class.java.name -> _7_BottlerScreen(game)
        _8_BottlerScreen::class.java.name -> _8_BottlerScreen(game)
        _9_BottlerScreen::class.java.name -> _9_BottlerScreen(game)
        _10_BottlerScreen::class.java.name -> _10_BottlerScreen(game)
        _11_BottlerScreen::class.java.name -> _11_BottlerScreen(game)
        _12_BottlerScreen::class.java.name -> _12_BottlerScreen(game)

        else -> BotlerMenuScreen(game)
    }

}


