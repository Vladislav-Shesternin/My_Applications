package investgroup.program.gaz.game.manager

import com.badlogic.gdx.Gdx
import investgroup.program.gaz.game.LibGDXGame
import investgroup.program.gaz.game.screens.LoaderScreen
import investgroup.program.gaz.game.screens.SecretScreen
import investgroup.program.gaz.game.screens.TerbalinoScreen
import investgroup.program.gaz.game.screens.UzumakiScreen
import investgroup.program.gaz.game.utils.advanced.AdvancedScreen
import investgroup.program.gaz.game.utils.runGDX

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
        SecretScreen::class.java.name    -> SecretScreen(game)
        TerbalinoScreen::class.java.name -> TerbalinoScreen(game)
        UzumakiScreen::class.java.name -> UzumakiScreen(game)
        else                            -> SecretScreen(game)
    }

}