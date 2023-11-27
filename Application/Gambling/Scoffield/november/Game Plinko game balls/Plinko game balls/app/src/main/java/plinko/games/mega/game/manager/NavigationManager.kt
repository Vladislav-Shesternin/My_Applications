package plinko.games.mega.game.manager

import com.badlogic.gdx.Gdx
import plinko.games.mega.game.LibGDXGame
import plinko.games.mega.game.screens.GameScreen_1
import plinko.games.mega.game.screens.GameScreen_2
import plinko.games.mega.game.screens.LevelScreen
import plinko.games.mega.game.screens.LoaderScreen
import plinko.games.mega.game.screens.MenuScreen
import plinko.games.mega.game.screens.RulesScreen
import plinko.games.mega.game.screens.SettingsScreen
import plinko.games.mega.game.utils.advanced.AdvancedScreen
import plinko.games.mega.game.utils.runGDX

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
        LevelScreen    ::class.java.name -> LevelScreen(game)
        RulesScreen    ::class.java.name -> RulesScreen(game)
        SettingsScreen ::class.java.name -> SettingsScreen(game)
        GameScreen_1   ::class.java.name -> GameScreen_1(game)
        GameScreen_2   ::class.java.name -> GameScreen_2(game)

        else -> MenuScreen(game)
    }

}