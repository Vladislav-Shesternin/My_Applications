package samoa.molly.dolina.radoste.game.manager

import com.badlogic.gdx.Gdx
import samoa.molly.dolina.radoste.game.LibGDXGame
import samoa.molly.dolina.radoste.game.screens.KKDScreen
import samoa.molly.dolina.radoste.game.screens.KKJScreen
import samoa.molly.dolina.radoste.game.screens.KKScreen
import samoa.molly.dolina.radoste.game.screens.LoaderScreen
import samoa.molly.dolina.radoste.game.screens.MenuScreen
import samoa.molly.dolina.radoste.game.utils.advanced.AdvancedScreen
import samoa.molly.dolina.radoste.game.utils.runGDX

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
        LoaderScreen ::class.java.name -> LoaderScreen(game)
        MenuScreen   ::class.java.name -> MenuScreen(game)
        KKScreen     ::class.java.name -> KKScreen(game)
        KKJScreen    ::class.java.name -> KKJScreen(game)
        KKDScreen    ::class.java.name -> KKDScreen(game)

        else -> MenuScreen(game)
    }

}