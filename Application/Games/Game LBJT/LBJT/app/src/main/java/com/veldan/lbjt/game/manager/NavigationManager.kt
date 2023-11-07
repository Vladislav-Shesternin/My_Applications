package com.veldan.lbjt.game.manager

import com.badlogic.gdx.Gdx
import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.screens.AboutAuthorScreen
import com.veldan.lbjt.game.screens.CommentsScreen
import com.veldan.lbjt.game.screens.LoaderScreen
import com.veldan.lbjt.game.screens.MenuScreen
import com.veldan.lbjt.game.screens.SettingsScreen
import com.veldan.lbjt.game.screens.TutorialIntroductionScreen
import com.veldan.lbjt.game.screens.TutorialsScreen
import com.veldan.lbjt.game.screens.tutorialsScreen.GeneralInformationScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedScreen
import com.veldan.lbjt.game.utils.runGDX

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
        LoaderScreen              ::class.java.name -> LoaderScreen(game)
        MenuScreen                ::class.java.name -> MenuScreen(game)
        TutorialIntroductionScreen::class.java.name -> TutorialIntroductionScreen(game)
        SettingsScreen            ::class.java.name -> SettingsScreen(game)
        AboutAuthorScreen         ::class.java.name -> AboutAuthorScreen(game)
        CommentsScreen            ::class.java.name -> CommentsScreen(game)
        TutorialsScreen           ::class.java.name -> TutorialsScreen(game)
        // Tutorials Screens
        GeneralInformationScreen::class.java.name -> GeneralInformationScreen(game)

        else -> MenuScreen(game)
    }

}