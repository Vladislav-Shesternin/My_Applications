package finalizer.masturbaizer.lotos.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import finalizer.masturbaizer.lotos.MainActivity
import finalizer.masturbaizer.lotos.game.manager.NavigationManager
import finalizer.masturbaizer.lotos.game.manager.SpriteManager
import finalizer.masturbaizer.lotos.game.manager.util.SpriteUtil
import finalizer.masturbaizer.lotos.game.screens.LoaderScreen
import finalizer.masturbaizer.lotos.game.utils.advanced.AdvancedGame
import finalizer.masturbaizer.lotos.game.utils.disposeAll
import finalizer.masturbaizer.lotos.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager private set

    val spriteUtil by lazy { SpriteUtil.CommonAssets() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        navigationManager.navigate(LoaderScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(Color.WHITE)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            disposeAll(assetManager)
            disposableSet.disposeAll()
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}