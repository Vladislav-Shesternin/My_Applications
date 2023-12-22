package newyearpuz.lessons.forditky.game.manager

import com.badlogic.gdx.Gdx
import newyearpuz.lessons.forditky.game.LibGDXGame
import newyearpuz.lessons.forditky.game.screens.PuzzleScreen
import newyearpuz.lessons.forditky.game.screens.PuzzleSplashScreen
import newyearpuz.lessons.forditky.game.screens.PuzzleMenuScreen
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedScreen
import newyearpuz.lessons.forditky.game.utils.runGDX

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
        PuzzleSplashScreen     ::class.java.name  -> PuzzleSplashScreen(game)
        PuzzleMenuScreen        ::class.java.name -> PuzzleMenuScreen(game)
        PuzzleScreen        ::class.java.name     -> PuzzleScreen(game)

        else                                      -> PuzzleMenuScreen(game)
    }

}