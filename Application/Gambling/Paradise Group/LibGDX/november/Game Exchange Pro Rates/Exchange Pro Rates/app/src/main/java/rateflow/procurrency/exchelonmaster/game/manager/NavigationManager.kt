package rateflow.procurrency.exchelonmaster.game.manager

import com.badlogic.gdx.Gdx
import rateflow.procurrency.exchelonmaster.game.LibGDXGame
import rateflow.procurrency.exchelonmaster.game.screens.AlphaAScreen
import rateflow.procurrency.exchelonmaster.game.screens.GolemScreen
import rateflow.procurrency.exchelonmaster.game.screens.PaladiScreen
import rateflow.procurrency.exchelonmaster.game.screens.ResultOprosaScreen
import rateflow.procurrency.exchelonmaster.game.utils.advanced.AdvancedScreen
import rateflow.procurrency.exchelonmaster.game.utils.runGDX

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
        PaladiScreen::class.java.name -> PaladiScreen(game)
        GolemScreen::class.java.name  -> GolemScreen(game)
        AlphaAScreen::class.java.name  -> AlphaAScreen(game)
        ResultOprosaScreen::class.java.name  -> ResultOprosaScreen(game)
        else                          -> GolemScreen(game)
    }

}