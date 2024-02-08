package fortunetiger.you.luck.game.manager

import com.badlogic.gdx.Gdx
import fortunetiger.you.luck.game.LibGDXGame
import fortunetiger.you.luck.game.screens.LoaLevelScreen
import fortunetiger.you.luck.game.screens.LoaMenuScreen
import fortunetiger.you.luck.game.screens.LoaLoadScreen
import fortunetiger.you.luck.game.screens.LoaPuzzleScreen
import fortunetiger.you.luck.game.screens.LoaRulesScreen
import fortunetiger.you.luck.game.screens.LoaSettingsScreen
import fortunetiger.you.luck.game.utils.advanced.AdvancedScreen
import fortunetiger.you.luck.game.utils.runGDX

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
        LoaLoadScreen     ::class.java.name -> LoaLoadScreen(game)
        LoaMenuScreen     ::class.java.name -> LoaMenuScreen(game)
        LoaRulesScreen    ::class.java.name -> LoaRulesScreen(game)
        LoaSettingsScreen ::class.java.name -> LoaSettingsScreen(game)
        LoaLevelScreen    ::class.java.name -> LoaLevelScreen(game)
        LoaPuzzleScreen   ::class.java.name -> LoaPuzzleScreen(game)

        else -> LoaMenuScreen(game)
    }

}