package lycky.fortune.tiger.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import lycky.fortune.tiger.MainActivity
import lycky.fortune.tiger.game.manager.MusicManager
import lycky.fortune.tiger.game.manager.NavigationManager
import lycky.fortune.tiger.game.manager.SoundManager
import lycky.fortune.tiger.game.manager.SpriteManager
import lycky.fortune.tiger.game.manager.util.MusicUtil
import lycky.fortune.tiger.game.manager.util.SoundUtil
import lycky.fortune.tiger.game.manager.util.SpriteUtil
import lycky.fortune.tiger.game.screens.CoolTigerLoadingScreen
import lycky.fortune.tiger.game.utils.advanced.AdvancedGame
import lycky.fortune.tiger.game.utils.disposeAll
import lycky.fortune.tiger.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val splashAssets by lazy { SpriteUtil.SplashAssets() }
    val gameAssets   by lazy { SpriteUtil.GameAssets() }

    var backgroundColor = Color.BLACK
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(CoolTigerLoadingScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(backgroundColor)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            disposableSet.disposeAll()
            disposeAll(musicUtil, assetManager)
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}