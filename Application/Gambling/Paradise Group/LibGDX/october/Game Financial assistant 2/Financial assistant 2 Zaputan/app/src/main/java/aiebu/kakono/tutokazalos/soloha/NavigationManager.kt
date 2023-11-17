package aiebu.kakono.tutokazalos.soloha

import com.badlogic.gdx.Gdx
import aiebu.kakono.tutokazalos.LibGDXGame
import aiebu.kakono.tutokazalos.IbizjScreen
import aiebu.kakono.tutokazalos.VstrechaScreen
import aiebu.kakono.tutokazalos.LoaderScreen
import aiebu.kakono.tutokazalos.SaskapotScreen
import aiebu.kakono.tutokazalos.soloha.pisoha.front.AdvancedScreen
import aiebu.kakono.tutokazalos.soloha.pisoha.runGDX

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
        VstrechaScreen::class.java.name -> VstrechaScreen(game)
        SaskapotScreen::class.java.name -> SaskapotScreen(game)
        IbizjScreen::class.java.name    -> IbizjScreen(game)
        else                            -> VstrechaScreen(game)
    }

}