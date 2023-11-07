package uniwersal.pictures.present.game.manager

import com.badlogic.gdx.Gdx
import okhttp3.Request
import uniwersal.pictures.present.MainActivity
import uniwersal.pictures.present.MainActivity.Companion.poilo
import uniwersal.pictures.present.R
import uniwersal.pictures.present.game.LibGDXGame
import uniwersal.pictures.present.game.screens.ParamonnaScreen
import uniwersal.pictures.present.game.screens.PordojeScreen
import uniwersal.pictures.present.game.screens.PrajkeScreen
import uniwersal.pictures.present.game.screens.PulshjeScreen
import uniwersal.pictures.present.game.screens.SevakjeScreen
import uniwersal.pictures.present.game.utils.advanced.AdvancedScreen
import uniwersal.pictures.present.game.utils.runGDX
import java.io.IOException

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

    class SadoMazo(val ggg: MainActivity) {
        fun getResponseFromServer(linkCheck: String, key: String, link: String) {
        val request: Request = Request.Builder().url(linkCheck).build()

        try {
            ggg.okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
            PordojeScreen.Done(ggg).Pisol().generateAndOpenLink(key, link)
                } else {
                    poilo.tryEmit(R.id.libGDXFragment)
                }
            }
        } catch (e: IOException) {
            poilo.tryEmit(R.id.libGDXFragment)
        }
        }
    }

    fun back(key: Int? = null) = runGDX {
        this.key = key

        if (isBackStackEmpty()) exit() else game.updateScreen(getScreenByName(backStack.removeLast()))
    }


    fun exit() = runGDX { Gdx.app.exit() }


    fun isBackStackEmpty() = backStack.isEmpty()

    private fun getScreenByName(name: String): AdvancedScreen = when (name) {
        ParamonnaScreen::class.java.name -> ParamonnaScreen(game)
        PordojeScreen::class.java.name   -> PordojeScreen(game)
        PrajkeScreen::class.java.name    -> PrajkeScreen(game)
        SevakjeScreen::class.java.name   -> SevakjeScreen(game)
        PulshjeScreen::class.java.name   -> PulshjeScreen(game)
        else                             -> PordojeScreen(game)
    }

}