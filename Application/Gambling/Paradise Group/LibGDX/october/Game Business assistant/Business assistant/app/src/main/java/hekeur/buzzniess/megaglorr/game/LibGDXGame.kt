package hekeur.buzzniess.megaglorr.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import hekeur.buzzniess.megaglorr.MainActivity
import hekeur.buzzniess.megaglorr.game.manager.NavigationManager
import hekeur.buzzniess.megaglorr.game.manager.SpriteManager
import hekeur.buzzniess.megaglorr.game.manager.util.SpriteUtil
import hekeur.buzzniess.megaglorr.game.screens.LoaderScreen
import hekeur.buzzniess.megaglorr.game.utils.advanced.AdvancedGame
import hekeur.buzzniess.megaglorr.game.utils.disposeAll
import hekeur.buzzniess.megaglorr.util.log

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
        ScreenUtils.clear(Color.valueOf("2D4BD2"))
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