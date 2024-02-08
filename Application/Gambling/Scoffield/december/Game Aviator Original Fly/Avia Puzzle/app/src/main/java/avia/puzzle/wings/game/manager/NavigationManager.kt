package avia.puzzle.wings.game.manager

import avia.puzzle.wings.game.LibGDXGame
import avia.puzzle.wings.game.screens.AviatorLoadingScreen
import avia.puzzle.wings.game.screens.AviatorMenuScreen
import avia.puzzle.wings.game.screens.AviatorPlaygraundScreen
import avia.puzzle.wings.game.screens.AviatorPuzzleScreen
import avia.puzzle.wings.game.screens.AviatorRulesScreen
import avia.puzzle.wings.game.screens.AviatorSettingsScreen
import avia.puzzle.wings.game.utils.advanced.AdvancedScreen
import avia.puzzle.wings.game.utils.runGDX
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
        AviatorLoadingScreen   ::class.java.name -> AviatorLoadingScreen(game)
        AviatorMenuScreen      ::class.java.name -> AviatorMenuScreen(game)
        AviatorRulesScreen     ::class.java.name -> AviatorRulesScreen(game)
        AviatorSettingsScreen  ::class.java.name -> AviatorSettingsScreen(game)
        AviatorPuzzleScreen    ::class.java.name -> AviatorPuzzleScreen(game)
        AviatorPlaygraundScreen::class.java.name -> AviatorPlaygraundScreen(game)

        else -> AviatorMenuScreen(game)
    }

}