package pro.ultimate.saver.game.manager

import com.badlogic.gdx.Gdx
import pro.ultimate.saver.game.LibGDXGame
import pro.ultimate.saver.game.screens.BalabolinoScreen
import pro.ultimate.saver.game.screens.HuedrochevoScreen
import pro.ultimate.saver.game.screens.LoaderScreen
import pro.ultimate.saver.game.screens.MasakaScreen
import pro.ultimate.saver.game.utils.advanced.AdvancedScreen
import pro.ultimate.saver.game.utils.runGDX

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
        BalabolinoScreen::class.java.name -> BalabolinoScreen(game)
        MasakaScreen::class.java.name     -> MasakaScreen(game)
        HuedrochevoScreen::class.java.name     -> HuedrochevoScreen(game)
        else                              -> BalabolinoScreen(game)
    }

}