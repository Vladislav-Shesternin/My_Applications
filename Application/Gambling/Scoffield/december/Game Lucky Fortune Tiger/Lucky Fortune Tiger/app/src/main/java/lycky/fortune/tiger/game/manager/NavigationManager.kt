package lycky.fortune.tiger.game.manager

import com.badlogic.gdx.Gdx
import lycky.fortune.tiger.game.LibGDXGame
import lycky.fortune.tiger.game.screens.MoreButtonScreen
import lycky.fortune.tiger.game.screens.CoolTigerLoadingScreen
import lycky.fortune.tiger.game.screens.GoodLuckRulesScreen
import lycky.fortune.tiger.game.screens.ManyToysScreen
import lycky.fortune.tiger.game.screens.VolumeNotificationScreen
import lycky.fortune.tiger.game.utils.advanced.AdvancedScreen
import lycky.fortune.tiger.game.utils.runGDX

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
        CoolTigerLoadingScreen   ::class.java.name -> CoolTigerLoadingScreen(game)
        MoreButtonScreen         ::class.java.name -> MoreButtonScreen(game)
        GoodLuckRulesScreen      ::class.java.name -> GoodLuckRulesScreen(game)
        VolumeNotificationScreen ::class.java.name -> VolumeNotificationScreen(game)
        ManyToysScreen           ::class.java.name -> ManyToysScreen(game)

        else -> MoreButtonScreen(game)
    }

}