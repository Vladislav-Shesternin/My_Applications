package com.boo.koftre.sure.game.game.manager

import com.badlogic.gdx.Gdx
import com.boo.koftre.sure.game.game.LibGDXGame
import com.boo.koftre.sure.game.game.screens.BParaScreen
import com.boo.koftre.sure.game.game.screens.ExitScreen
import com.boo.koftre.sure.game.game.screens.LoaderScreen
import com.boo.koftre.sure.game.game.screens.ResultScreen
import com.boo.koftre.sure.game.game.screens.common.SettingsScreen
import com.boo.koftre.sure.game.game.screens.template_screen.MenuScreen
import com.boo.koftre.sure.game.game.screens.template_screen.RulesScreen
import com.boo.koftre.sure.game.game.utils.advanced.AdvancedScreen
import com.boo.koftre.sure.game.game.utils.runGDX

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
        LoaderScreen  ::class.java.name -> LoaderScreen(game)
        MenuScreen    ::class.java.name -> MenuScreen(game)
        SettingsScreen::class.java.name -> SettingsScreen(game)
        RulesScreen   ::class.java.name -> RulesScreen(game)
        BParaScreen   ::class.java.name -> BParaScreen(game)

        ResultScreen  ::class.java.name -> ResultScreen(game)
        ExitScreen    ::class.java.name -> ExitScreen(game)

        else -> MenuScreen(game)
    }

}