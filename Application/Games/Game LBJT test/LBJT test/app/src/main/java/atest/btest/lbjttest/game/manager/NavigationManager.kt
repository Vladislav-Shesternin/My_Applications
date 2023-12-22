package atest.btest.lbjttest.game.manager

import atest.btest.lbjttest.game.LibGDXGame
import atest.btest.lbjttest.game.screens.LoadingScreen
import atest.btest.lbjttest.game.screens.MenuScreen
import atest.btest.lbjttest.game.screens.joints.DistanceJointScreen
import atest.btest.lbjttest.game.screens.joints.RevoluteJointScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedScreen
import atest.btest.lbjttest.game.utils.runGDX
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
        LoadingScreen::class.java.name -> LoadingScreen(game)
        MenuScreen   ::class.java.name -> MenuScreen(game)

        // Joints
        DistanceJointScreen::class.java.name -> DistanceJointScreen(game)
        RevoluteJointScreen::class.java.name -> RevoluteJointScreen(game)

        else -> LoadingScreen(game)
    }

}