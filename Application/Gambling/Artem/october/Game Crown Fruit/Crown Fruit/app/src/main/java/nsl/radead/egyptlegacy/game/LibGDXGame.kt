package nsl.radead.egyptlegacy.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import nsl.radead.egyptlegacy.game.manager.NavigationManager
import nsl.radead.egyptlegacy.game.manager.SpriteManager
import nsl.radead.egyptlegacy.game.manager.util.SpriteUtil
import nsl.radead.egyptlegacy.game.screens.LoaderScreen
import nsl.radead.egyptlegacy.game.utils.GameColor
import nsl.radead.egyptlegacy.game.utils.advanced.AdvancedGame
import nsl.radead.egyptlegacy.game.utils.disposeAll
import nsl.radead.egyptlegacy.util.log

class LibGDXGame(val activity: nsl.radead.egyptlegacy.MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set

    val spriteUtil by lazy { SpriteUtil.CommonAssets() }

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