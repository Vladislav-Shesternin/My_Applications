package lovely.gooden.nicertia.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import lovely.gooden.nicertia.MainActivity
import lovely.gooden.nicertia.game.manager.NavigationManager
import lovely.gooden.nicertia.game.manager.SpriteManager
import lovely.gooden.nicertia.game.manager.util.SpriteUtil
import lovely.gooden.nicertia.game.screens.LoaderScreen
import lovely.gooden.nicertia.game.utils.advanced.AdvancedGame
import lovely.gooden.nicertia.game.utils.disposeAll
import lovely.gooden.nicertia.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var assetManagerLocal: AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager private set

    val spriteUtil    by lazy { SpriteUtil.CommonAssets() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        assetManagerLocal = AssetManager(LocalFileHandleResolver())
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
            disposeAll(assetManager, assetManagerLocal)
            disposableSet.disposeAll()
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}