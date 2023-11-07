package fludilia.mamkinarow.lotrends.game.manager

import com.badlogic.gdx.Gdx
import fludilia.mamkinarow.lotrends.game.LibGDXGame
import fludilia.mamkinarow.lotrends.game.screens.LoaderScreen
import fludilia.mamkinarow.lotrends.game.screens.BannerScreen
import fludilia.mamkinarow.lotrends.game.screens.DobavitScreen
import fludilia.mamkinarow.lotrends.game.utils.advanced.AdvancedScreen
import fludilia.mamkinarow.lotrends.game.utils.runGDX

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
        BannerScreen::class.java.name -> BannerScreen(game)
        DobavitScreen::class.java.name -> DobavitScreen(game)
        else                          -> BannerScreen(game)
    }

}