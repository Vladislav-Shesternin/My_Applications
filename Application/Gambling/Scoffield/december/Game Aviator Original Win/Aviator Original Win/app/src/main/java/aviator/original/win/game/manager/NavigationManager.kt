package aviator.original.win.game.manager

import aviator.original.win.game.LibGDXGame
import aviator.original.win.game.screens.OriginalGameScreen
import aviator.original.win.game.screens.OriginalLoadingScreen
import aviator.original.win.game.screens.OriginalMenuScreen
import aviator.original.win.game.screens.OriginalRulesScreen
import aviator.original.win.game.screens.OriginalSettingsScreen
import aviator.original.win.game.screens.OriginalShopScreen
import aviator.original.win.game.utils.advanced.AdvancedScreen
import aviator.original.win.game.utils.runGDX
import com.badlogic.gdx.Gdx

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
        OriginalLoadingScreen ::class.java.name -> OriginalLoadingScreen(game)
        OriginalMenuScreen    ::class.java.name -> OriginalMenuScreen(game)
        OriginalRulesScreen   ::class.java.name -> OriginalRulesScreen(game)
        OriginalSettingsScreen::class.java.name -> OriginalSettingsScreen(game)
        OriginalShopScreen    ::class.java.name -> OriginalShopScreen(game)
        OriginalGameScreen    ::class.java.name -> OriginalGameScreen(game)

        else -> OriginalMenuScreen(game)
    }

}