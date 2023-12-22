package indiaquiz.winterenter.holiwensday.game.manager

import com.badlogic.gdx.Gdx
import indiaquiz.winterenter.holiwensday.game.LibGDXGame
import indiaquiz.winterenter.holiwensday.game.screens.HappyMenuScreen
import indiaquiz.winterenter.holiwensday.game.screens.HappyQuizScreen
import indiaquiz.winterenter.holiwensday.game.screens.HappySplashScreen
import indiaquiz.winterenter.holiwensday.game.utils.advanced.AdvancedScreen
import indiaquiz.winterenter.holiwensday.game.utils.runGDX

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
        HappySplashScreen::class.java.name -> HappySplashScreen(game)
        HappyMenuScreen  ::class.java.name -> HappyMenuScreen(game)
        HappyQuizScreen  ::class.java.name -> HappyQuizScreen(game)

        else -> HappyMenuScreen(game)
    }

}