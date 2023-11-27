package samoa.molly.dolina.radoste.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import samoa.molly.dolina.radoste.MainActivity
import samoa.molly.dolina.radoste.game.manager.NavigationManager
import samoa.molly.dolina.radoste.game.manager.SpriteManager
import samoa.molly.dolina.radoste.game.manager.SpriteUtil
import samoa.molly.dolina.radoste.game.screens.LoaderScreen
import samoa.molly.dolina.radoste.game.utils.advanced.AdvancedGame
import samoa.molly.dolina.radoste.game.utils.disposeAll
import samoa.molly.dolina.radoste.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set

    var backgroundColor = Color.valueOf("23262F")

    val assets by lazy { SpriteUtil.CommonAssets() }

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
            disposeAll(assetManager)
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}