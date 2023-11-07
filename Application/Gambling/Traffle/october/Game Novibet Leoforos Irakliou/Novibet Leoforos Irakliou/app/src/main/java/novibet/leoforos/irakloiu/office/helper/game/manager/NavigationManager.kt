package novibet.leoforos.irakloiu.office.helper.game.manager

import com.badlogic.gdx.Gdx
import novibet.leoforos.irakloiu.office.helper.game.LibGDXGame
import novibet.leoforos.irakloiu.office.helper.game.screens.AboutUsScreen
import novibet.leoforos.irakloiu.office.helper.game.screens.CalendarScreen
import novibet.leoforos.irakloiu.office.helper.game.screens.LoaderScreen
import novibet.leoforos.irakloiu.office.helper.game.screens.RulesScreen
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedScreen
import novibet.leoforos.irakloiu.office.helper.game.utils.runGDX

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
        LoaderScreen  ::class.java.name  -> LoaderScreen(game)
        RulesScreen   ::class.java.name  -> RulesScreen(game)
        AboutUsScreen ::class.java.name  -> AboutUsScreen(game)
        CalendarScreen::class.java.name  -> CalendarScreen(game)
        else                             -> RulesScreen(game)
    }

}