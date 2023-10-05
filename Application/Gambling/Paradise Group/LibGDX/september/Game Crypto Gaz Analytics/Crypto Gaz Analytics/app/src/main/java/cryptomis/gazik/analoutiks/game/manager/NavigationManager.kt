package cryptomis.gazik.analoutiks.game.manager

import com.badlogic.gdx.Gdx
import cryptomis.gazik.analoutiks.game.LibGDXGame
import cryptomis.gazik.analoutiks.game.screens.CryptoItemScreen
import cryptomis.gazik.analoutiks.game.screens.LoaderScreen
import cryptomis.gazik.analoutiks.game.screens.CryptoListScreen
import cryptomis.gazik.analoutiks.game.screens.StartScreen
import cryptomis.gazik.analoutiks.game.utils.advanced.AdvancedScreen
import cryptomis.gazik.analoutiks.game.utils.runGDX

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
        CryptoListScreen::class.java.name -> CryptoListScreen(game)
        CryptoItemScreen::class.java.name -> CryptoItemScreen(game)
        StartScreen::class.java.name      -> StartScreen(game)
        else                              -> CryptoListScreen(game)
    }

}