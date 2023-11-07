package italodisco.fernando.lucherano.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import italodisco.fernando.lucherano.MainActivity
import italodisco.fernando.lucherano.game.manager.NavigationManager
import italodisco.fernando.lucherano.game.manager.SpriteManagerUUU
import italodisco.fernando.lucherano.game.manager.util.TutaSparta
import italodisco.fernando.lucherano.game.screens.LoaderScreen
import italodisco.fernando.lucherano.game.utils.advanced.AdvancedGame
import italodisco.fernando.lucherano.game.utils.disposeAll
import italodisco.fernando.lucherano.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var assetManagerLocal: AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManagerUUU private set

    val spriteUtil    by lazy { TutaSparta.Gera() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        assetManagerLocal = AssetManager(LocalFileHandleResolver())
        spriteManager     = SpriteManagerUUU(assetManager)

        navigationManager.navigate(LoaderScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(Color.BLACK)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            disposeAll(assetManager, assetManagerLocal)
            disposableSet.disposeAll()
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}