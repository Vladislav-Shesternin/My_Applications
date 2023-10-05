package monska.makkers.conver.currinci.game.manager

import com.badlogic.gdx.Gdx
import monska.makkers.conver.currinci.game.LibGDXGame
import monska.makkers.conver.currinci.game.screens.UtemScreen
import monska.makkers.conver.currinci.game.screens.LoaderScreen
import monska.makkers.conver.currinci.game.screens.VScreen
import monska.makkers.conver.currinci.game.screens.StartScreen
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedScreen
import monska.makkers.conver.currinci.game.utils.runGDX

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
        LoaderScreen::class.java.name     -> LoaderScreen(game)
        VScreen::class.java.name     -> VScreen(game)
        UtemScreen::class.java.name  -> UtemScreen(game)
        StartScreen::class.java.name -> StartScreen(game)
        else                              -> VScreen(game)
    }

}