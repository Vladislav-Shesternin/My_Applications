package italodisco.fernando.lucherano.game.manager

import com.badlogic.gdx.Gdx
import italodisco.fernando.lucherano.game.LibGDXGame
import italodisco.fernando.lucherano.game.screens.KlounPerdun
import italodisco.fernando.lucherano.game.screens.LoaderScreen
import italodisco.fernando.lucherano.game.screens.FraoEngel
import italodisco.fernando.lucherano.game.screens.Lodogor
import italodisco.fernando.lucherano.game.utils.advanced.AdvancedScreen
import italodisco.fernando.lucherano.game.utils.runGDX

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
        LoaderScreen::class.java.name -> LoaderScreen(game)
        KlounPerdun::class.java.name -> KlounPerdun(game)
        FraoEngel::class.java.name   -> FraoEngel(game)
        Lodogor::class.java.name   -> Lodogor(game)
        else                         -> KlounPerdun(game)
    }

}