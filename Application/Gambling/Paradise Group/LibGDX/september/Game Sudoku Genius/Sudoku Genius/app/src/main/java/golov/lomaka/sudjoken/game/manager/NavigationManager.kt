package golov.lomaka.sudjoken.game.manager

import com.badlogic.gdx.Gdx
import golov.lomaka.sudjoken.game.LibGDXGame
import golov.lomaka.sudjoken.game.utils.advanced.AdvancedScreen
import golov.lomaka.sudjoken.game.utils.runGDX

class NavigationManager(val game: LibGDXGame) {

    private val backStack = mutableListOf<AdvancedScreen>()
    var key: Int? = null
        private set

    fun navigate(to: AdvancedScreen, from: AdvancedScreen? = null, key: Int? = null) {
        runGDX {
            this.key = key

            game.screen = to
            backStack.filter { it.name == to.name }.onEach { backStack.remove(it) }
            from?.let { f ->
                backStack.filter { it.name == f.name }.onEach { backStack.remove(it) }
                backStack.add(f)
            }
        }
    }

    fun back(key: Int? = null) {
        runGDX {
            this.key = key

            if (isBackStackEmpty()) exit()
            else game.screen = backStack.removeLast().javaClass.getDeclaredConstructor(LibGDXGame::class.java).newInstance(game)
        }
    }

    fun exit() {
        runGDX {
            Gdx.app.exit()
            //game.activity.exit()
        }
    }

    fun isBackStackEmpty() = backStack.isEmpty()

}