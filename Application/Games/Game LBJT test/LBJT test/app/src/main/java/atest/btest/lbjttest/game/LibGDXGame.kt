package atest.btest.lbjttest.game

import atest.btest.lbjttest.MainActivity
import atest.btest.lbjttest.game.manager.AllAssets
import atest.btest.lbjttest.game.manager.NavigationManager
import atest.btest.lbjttest.game.manager.SpriteManager
import atest.btest.lbjttest.game.screens.LoadingScreen
import atest.btest.lbjttest.game.utils.GameColor
import atest.btest.lbjttest.game.utils.advanced.AdvancedGame
import atest.btest.lbjttest.game.utils.disposeAll
import atest.btest.lbjttest.util.log
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set

    val assets by lazy { AllAssets() }

    var backgroundColor = GameColor.yan
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        navigationManager.navigate(LoadingScreen::class.java.name)
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