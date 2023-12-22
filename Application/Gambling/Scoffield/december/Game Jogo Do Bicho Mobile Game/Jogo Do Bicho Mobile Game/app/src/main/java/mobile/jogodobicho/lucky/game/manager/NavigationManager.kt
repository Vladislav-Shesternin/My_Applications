package mobile.jogodobicho.lucky.game.manager

import com.badlogic.gdx.Gdx
import mobile.jogodobicho.lucky.game.LibGDXGame
import mobile.jogodobicho.lucky.game.screens.BullChooseLevelScreen
import mobile.jogodobicho.lucky.game.screens.BullGameScreen
import mobile.jogodobicho.lucky.game.screens.BullLoadingScreen
import mobile.jogodobicho.lucky.game.screens.BullMenuScreen
import mobile.jogodobicho.lucky.game.screens.BullSettingsScreen
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedScreen
import mobile.jogodobicho.lucky.game.utils.runGDX

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
        BullLoadingScreen     ::class.java.name -> BullLoadingScreen(game)
        BullMenuScreen        ::class.java.name -> BullMenuScreen(game)
        BullSettingsScreen    ::class.java.name -> BullSettingsScreen(game)
        BullChooseLevelScreen ::class.java.name -> BullChooseLevelScreen(game)
        BullGameScreen        ::class.java.name -> BullGameScreen(game)

        else -> BullMenuScreen(game)
    }

}