package qbl.bisriymyach.QuickBall.pitopilot

import com.badlogic.gdx.Gdx
import qbl.bisriymyach.QuickBall.LibGDXGame
import qbl.bisriymyach.QuickBall.hotvils.Palibresko
import qbl.bisriymyach.QuickBall.hotvils.LoaderScreen
import qbl.bisriymyach.QuickBall.hotvils.Miski
import qbl.bisriymyach.QuickBall.hotvils.Riski
import qbl.bisriymyach.QuickBall.hotvils.Sitriska
import qbl.bisriymyach.QuickBall.fastergan.suchka
import qbl.bisriymyach.QuickBall.fastergan.runGDX

class neirofemka(val game: LibGDXGame) {

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

    private fun getScreenByName(name: String): suchka = when (name) {
        LoaderScreen::class.java.name -> LoaderScreen(game)
        Miski::class.java.name        -> Miski(game)
        Riski::class.java.name        -> Riski(game)
        Sitriska::class.java.name     -> Sitriska(game)
        Palibresko::class.java.name   -> Palibresko(game)

        else                          -> Miski(game)
    }

}