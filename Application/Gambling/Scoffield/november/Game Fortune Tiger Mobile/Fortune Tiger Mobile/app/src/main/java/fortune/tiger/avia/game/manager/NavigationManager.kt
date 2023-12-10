package fortune.tiger.avia.game.manager

import com.badlogic.gdx.Gdx
import fortune.tiger.avia.game.LibGDXGame
import fortune.tiger.avia.game.screens.IgrabelnaGraScreen
import fortune.tiger.avia.game.screens.IgrabelnaGraScreen2
import fortune.tiger.avia.game.screens.LeyresesScreen
import fortune.tiger.avia.game.screens.LoaderScreen
import fortune.tiger.avia.game.screens.MenuScreen
import fortune.tiger.avia.game.screens.TessingsScreen
import fortune.tiger.avia.game.screens.UlerusScreen
import fortune.tiger.avia.game.utils.advanced.AdvancedScreen
import fortune.tiger.avia.game.utils.runGDX

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
        LoaderScreen       ::class.java.name -> LoaderScreen(game)
        MenuScreen         ::class.java.name -> MenuScreen(game)
        UlerusScreen       ::class.java.name -> UlerusScreen(game)
        TessingsScreen     ::class.java.name -> TessingsScreen(game)
        LeyresesScreen     ::class.java.name -> LeyresesScreen(game)
        IgrabelnaGraScreen ::class.java.name -> IgrabelnaGraScreen(game)
        IgrabelnaGraScreen2::class.java.name -> IgrabelnaGraScreen2(game)

        else -> MenuScreen(game)
    }

}