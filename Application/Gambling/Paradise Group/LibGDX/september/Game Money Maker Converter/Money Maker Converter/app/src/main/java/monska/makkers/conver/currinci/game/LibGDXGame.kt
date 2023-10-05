package monska.makkers.conver.currinci.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import monska.makkers.conver.currinci.MainActivity
import monska.makkers.conver.currinci.game.manager.NavigationManager
import monska.makkers.conver.currinci.game.manager.SpriteManager
import monska.makkers.conver.currinci.game.manager.util.SpriteUtil
import monska.makkers.conver.currinci.game.screens.LoaderScreen
import monska.makkers.conver.currinci.game.utils.GameColor
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedGame
import monska.makkers.conver.currinci.game.utils.disposeAll
import monska.makkers.conver.currinci.util.log

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
        ScreenUtils.clear(GameColor.babka)
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