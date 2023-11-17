package italodisco.fernando.lucherano

import com.badlogic.gdx.Gdx
import italodisco.fernando.lucherano.iopartew.opOPa
import italodisco.fernando.lucherano.pistorNaD.KlounPerdun
import italodisco.fernando.lucherano.pistorNaD.ladsro.HeromantiaStrun
import italodisco.fernando.lucherano.iopartew.sandes.FraoEngel
import italodisco.fernando.lucherano.iopartew.sandes.Lodogor
import italodisco.fernando.lucherano.iopartew.pppp098.font.AdvancedScreen
import italodisco.fernando.lucherano.iopartew.pppp098.runGDX

class NaMaNa(val game: opOPa) {

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
        HeromantiaStrun::class.java.name -> HeromantiaStrun(game)
        KlounPerdun::class.java.name     -> KlounPerdun(game)
        FraoEngel::class.java.name    -> FraoEngel(game)
        Lodogor::class.java.name   -> Lodogor(game)
        else                         -> KlounPerdun(game)
    }

}