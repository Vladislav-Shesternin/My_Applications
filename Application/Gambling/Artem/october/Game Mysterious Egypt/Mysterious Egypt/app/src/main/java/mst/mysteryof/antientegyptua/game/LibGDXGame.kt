package mst.mysteryof.antientegyptua.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import mst.mysteryof.antientegyptua.game.manager.NavigationManager
import mst.mysteryof.antientegyptua.game.manager.SpriteManager
import mst.mysteryof.antientegyptua.game.manager.util.SpriteUtil
import mst.mysteryof.antientegyptua.game.screens.LoaderScreen
import mst.mysteryof.antientegyptua.game.utils.GameColor
import mst.mysteryof.antientegyptua.game.utils.advanced.AdvancedGame
import mst.mysteryof.antientegyptua.game.utils.disposeAll
import mst.mysteryof.antientegyptua.util.log

class LibGDXGame(val activity: mst.mysteryof.antientegyptua.MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set

    val spriteUtil       by lazy { SpriteUtil.CommonAssets() }
    val spriteUtilSplash by lazy { SpriteUtil.SplashAssets() }

    var backgroundColor = GameColor.background
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        navigationManager.navigate(LoaderScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(backgroundColor)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            disposableSet.disposeAll()
            disposeAll(assetManager)
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}