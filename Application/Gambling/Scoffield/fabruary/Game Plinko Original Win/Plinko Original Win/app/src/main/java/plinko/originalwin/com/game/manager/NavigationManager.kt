package plinko.originalwin.com.game.manager

import com.badlogic.gdx.Gdx
import plinko.originalwin.com.game.LibGDXGame
import plinko.originalwin.com.game.screens.GameOverScreen
import plinko.originalwin.com.game.screens.GameScreen
import plinko.originalwin.com.game.screens.LoaderScreen
import plinko.originalwin.com.game.screens.MenuScreen
import plinko.originalwin.com.game.utils.advanced.AdvancedScreen
import plinko.originalwin.com.game.utils.runGDX

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
        LoaderScreen   ::class.java.name -> LoaderScreen(game)
        MenuScreen     ::class.java.name -> MenuScreen(game)
        GameScreen     ::class.java.name -> GameScreen(game)
        GameOverScreen ::class.java.name -> GameOverScreen(game)

        else -> MenuScreen(game)
    }

}