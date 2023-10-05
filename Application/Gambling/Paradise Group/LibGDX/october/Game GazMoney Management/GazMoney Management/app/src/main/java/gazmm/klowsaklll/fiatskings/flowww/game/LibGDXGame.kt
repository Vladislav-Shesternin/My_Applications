package gazmm.klowsaklll.fiatskings.flowww.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import gazmm.klowsaklll.fiatskings.flowww.MainActivity
import gazmm.klowsaklll.fiatskings.flowww.game.manager.NavigationManager
import gazmm.klowsaklll.fiatskings.flowww.game.manager.SpriteManager
import gazmm.klowsaklll.fiatskings.flowww.game.manager.util.SpriteUtil
import gazmm.klowsaklll.fiatskings.flowww.game.screens.LoaderScreen
import gazmm.klowsaklll.fiatskings.flowww.game.utils.GameColor
import gazmm.klowsaklll.fiatskings.flowww.game.utils.advanced.AdvancedGame
import gazmm.klowsaklll.fiatskings.flowww.game.utils.disposeAll
import gazmm.klowsaklll.fiatskings.flowww.util.log

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