package rainbowriches.lucky.start.game.manager

import com.badlogic.gdx.Gdx
import rainbowriches.lucky.start.game.LibGDXGame
import rainbowriches.lucky.start.game.screens.ButtonsScreen
import rainbowriches.lucky.start.game.screens.EasyNormalHardScreen
import rainbowriches.lucky.start.game.screens.LevNorHardScreen
import rainbowriches.lucky.start.game.screens.LoaderScreen
import rainbowriches.lucky.start.game.screens.MusicSoundScreen
import rainbowriches.lucky.start.game.screens.SpecificationScreen
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen
import rainbowriches.lucky.start.game.utils.runGDX

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
        LoaderScreen         ::class.java.name -> LoaderScreen(game)
        ButtonsScreen        ::class.java.name -> ButtonsScreen(game)
        SpecificationScreen  ::class.java.name -> SpecificationScreen(game)
        MusicSoundScreen     ::class.java.name -> MusicSoundScreen(game)
        LevNorHardScreen     ::class.java.name -> LevNorHardScreen(game)
        EasyNormalHardScreen ::class.java.name -> EasyNormalHardScreen(game)

        else -> ButtonsScreen(game)
    }

}