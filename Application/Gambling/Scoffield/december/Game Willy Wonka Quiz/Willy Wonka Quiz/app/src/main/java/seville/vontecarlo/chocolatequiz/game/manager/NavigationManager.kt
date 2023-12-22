package seville.vontecarlo.chocolatequiz.game.manager

import com.badlogic.gdx.Gdx
import seville.vontecarlo.chocolatequiz.game.LibGDXGame
import seville.vontecarlo.chocolatequiz.game.screens.WonkaMenuScreen
import seville.vontecarlo.chocolatequiz.game.screens.WonkaQuizScreen
import seville.vontecarlo.chocolatequiz.game.screens.WonkaSplashScreen
import seville.vontecarlo.chocolatequiz.game.utils.advanced.AdvancedScreen
import seville.vontecarlo.chocolatequiz.game.utils.runGDX

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
        WonkaSplashScreen::class.java.name -> WonkaSplashScreen(game)
        WonkaMenuScreen  ::class.java.name -> WonkaMenuScreen(game)
        WonkaQuizScreen  ::class.java.name -> WonkaQuizScreen(game)

        else -> WonkaMenuScreen(game)
    }

}