package plinko.originalwin.com.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import plinko.originalwin.com.MainActivity
import plinko.originalwin.com.game.manager.NavigationManager
import plinko.originalwin.com.game.manager.SoundManager
import plinko.originalwin.com.game.manager.SpriteManager
import plinko.originalwin.com.game.manager.util.SoundUtil
import plinko.originalwin.com.game.manager.util.SpriteUtil
import plinko.originalwin.com.game.screens.LoaderScreen
import plinko.originalwin.com.game.utils.advanced.AdvancedGame
import plinko.originalwin.com.game.utils.disposeAll
import plinko.originalwin.com.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var soundManager     : SoundManager      private set

    val soundUtil by lazy { SoundUtil()    }
    val allAssets by lazy { SpriteUtil.AllAssets() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(LoaderScreen::class.java.name)
    }

    private val backgroundColor = Color.valueOf("3A125E")

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