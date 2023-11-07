package uniwersal.pictures.present.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import uniwersal.pictures.present.MainActivity
import uniwersal.pictures.present.game.manager.NavigationManager
import uniwersal.pictures.present.game.manager.SpriteManager
import uniwersal.pictures.present.game.manager.util.SpriteUtil
import uniwersal.pictures.present.game.screens.ParamonnaScreen
import uniwersal.pictures.present.game.utils.advanced.AdvancedGame
import uniwersal.pictures.present.game.utils.disposeAll
import uniwersal.pictures.present.util.log

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

        navigationManager.navigate(ParamonnaScreen::class.java.name)
    }

    var ccc = Color.valueOf("F49425")

    override fun render() {
        ScreenUtils.clear(ccc)
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