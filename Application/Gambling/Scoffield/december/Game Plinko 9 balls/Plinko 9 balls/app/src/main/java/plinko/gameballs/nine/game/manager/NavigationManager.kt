package plinko.gameballs.nine.game.manager

import com.badlogic.gdx.Gdx
import plinko.gameballs.nine.game.LibGDXGame
import plinko.gameballs.nine.game.screens.ManyPlatformsScreen
import plinko.gameballs.nine.game.screens.MenuButtonsScreen
import plinko.gameballs.nine.game.screens.RedBallLoadingScreen
import plinko.gameballs.nine.game.screens.RulesEverythingScreen
import plinko.gameballs.nine.game.screens.SelectBallScreen
import plinko.gameballs.nine.game.screens.SettingsPlayScreen
import plinko.gameballs.nine.game.utils.advanced.AdvancedScreen
import plinko.gameballs.nine.game.utils.runGDX

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
        RedBallLoadingScreen   ::class.java.name -> RedBallLoadingScreen(game)
        MenuButtonsScreen      ::class.java.name -> MenuButtonsScreen(game)
        RulesEverythingScreen  ::class.java.name -> RulesEverythingScreen(game)
        SettingsPlayScreen     ::class.java.name -> SettingsPlayScreen(game)
        SelectBallScreen       ::class.java.name -> SelectBallScreen(game)
        ManyPlatformsScreen    ::class.java.name -> ManyPlatformsScreen(game)

        else -> MenuButtonsScreen(game)
    }

}