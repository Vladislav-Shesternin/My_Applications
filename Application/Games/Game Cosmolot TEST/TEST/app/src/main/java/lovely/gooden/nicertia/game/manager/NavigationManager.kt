package lovely.gooden.nicertia.game.manager

import com.badlogic.gdx.Gdx
import lovely.gooden.nicertia.game.LibGDXGame
import lovely.gooden.nicertia.game.screens.FasadikScreen
import lovely.gooden.nicertia.game.screens.LoaderScreen
import lovely.gooden.nicertia.game.screens.PedanticScreen
import lovely.gooden.nicertia.game.utils.advanced.AdvancedScreen
import lovely.gooden.nicertia.game.utils.runGDX

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
        LoaderScreen::class.java.name   -> LoaderScreen(game)
        FasadikScreen::class.java.name  -> FasadikScreen(game)
        PedanticScreen::class.java.name -> PedanticScreen(game)
        else                            -> FasadikScreen(game)
    }

}