package jogo.fortunetiger.tighrino.game.manager

import com.badlogic.gdx.Gdx
import jogo.fortunetiger.tighrino.game.LibGDXGame
import jogo.fortunetiger.tighrino.game.screens.TChooseScreen
import jogo.fortunetiger.tighrino.game.screens.TGameScreen
import jogo.fortunetiger.tighrino.game.screens.TLoaderScreen
import jogo.fortunetiger.tighrino.game.screens.TMenuScreen
import jogo.fortunetiger.tighrino.game.screens.TRulesScreen
import jogo.fortunetiger.tighrino.game.screens.TSettingsScreen
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedScreen
import jogo.fortunetiger.tighrino.game.utils.runGDX

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
        TLoaderScreen   ::class.java.name -> TLoaderScreen(game)
        TMenuScreen     ::class.java.name -> TMenuScreen(game)
        TRulesScreen    ::class.java.name -> TRulesScreen(game)
        TSettingsScreen ::class.java.name -> TSettingsScreen(game)
        TChooseScreen   ::class.java.name -> TChooseScreen(game)
        TGameScreen     ::class.java.name -> TGameScreen(game)

        else                                  -> TLoaderScreen(game)
    }

}