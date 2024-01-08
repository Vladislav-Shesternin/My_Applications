package plinko.testyouluck.com.game.manager

import com.badlogic.gdx.Gdx
import plinko.testyouluck.com.game.LibGDXGame
import plinko.testyouluck.com.game.screens.PlinkoGameScreen
import plinko.testyouluck.com.game.screens.PlinkoLoaderScreen
import plinko.testyouluck.com.game.screens.PlinkoMenuScreen
import plinko.testyouluck.com.game.screens.PlinkoRulesScreen
import plinko.testyouluck.com.game.screens.PlinkoSettingsScreen
import plinko.testyouluck.com.game.screens.PlinkoShopScreen
import plinko.testyouluck.com.game.utils.advanced.AdvancedScreen
import plinko.testyouluck.com.game.utils.runGDX

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
        PlinkoLoaderScreen  ::class.java.name -> PlinkoLoaderScreen(game)
        PlinkoMenuScreen    ::class.java.name -> PlinkoMenuScreen(game)
        PlinkoRulesScreen   ::class.java.name -> PlinkoRulesScreen(game)
        PlinkoSettingsScreen::class.java.name -> PlinkoSettingsScreen(game)
        PlinkoGameScreen    ::class.java.name -> PlinkoGameScreen(game)
        PlinkoShopScreen    ::class.java.name -> PlinkoShopScreen(game)

        else -> PlinkoMenuScreen(game)
    }

}