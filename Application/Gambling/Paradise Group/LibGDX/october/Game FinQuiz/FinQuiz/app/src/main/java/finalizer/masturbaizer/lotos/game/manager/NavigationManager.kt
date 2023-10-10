package finalizer.masturbaizer.lotos.game.manager

import com.badlogic.gdx.Gdx
import finalizer.masturbaizer.lotos.game.LibGDXGame
import finalizer.masturbaizer.lotos.game.screens.ForestScreen
import finalizer.masturbaizer.lotos.game.screens.WinnerScreen
import finalizer.masturbaizer.lotos.game.screens.VoprosikiScreen
import finalizer.masturbaizer.lotos.game.screens.LoaderScreen
import finalizer.masturbaizer.lotos.game.utils.advanced.AdvancedScreen
import finalizer.masturbaizer.lotos.game.utils.runGDX

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
        LoaderScreen::class.java.name    -> LoaderScreen(game)
        WinnerScreen::class.java.name    -> WinnerScreen(game)
        VoprosikiScreen::class.java.name -> VoprosikiScreen(game)
        ForestScreen::class.java.name    -> ForestScreen(game)
        else                             -> WinnerScreen(game)
    }

}