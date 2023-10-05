package gazmm.klowsaklll.fiatskings.flowww.game.manager

import com.badlogic.gdx.Gdx
import gazmm.klowsaklll.fiatskings.flowww.game.LibGDXGame
import gazmm.klowsaklll.fiatskings.flowww.game.screens.AnalitikaScreen
import gazmm.klowsaklll.fiatskings.flowww.game.screens.CatalogScreen
import gazmm.klowsaklll.fiatskings.flowww.game.screens.HomekScreen
import gazmm.klowsaklll.fiatskings.flowww.game.screens.LoaderScreen
import gazmm.klowsaklll.fiatskings.flowww.game.screens.StartScreen
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedScreen
import gazmm.klowsaklll.fiatskings.flowww.game.utils.runGDX

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
        HomekScreen::class.java.name     -> HomekScreen(game)
        CatalogScreen::class.java.name   -> CatalogScreen(game)
        StartScreen::class.java.name     -> StartScreen(game)
        AnalitikaScreen::class.java.name -> AnalitikaScreen(game)
        else                             -> StartScreen(game)
    }

}