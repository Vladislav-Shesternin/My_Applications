package rainbowriches.lucky.start.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import rainbowriches.lucky.start.MainActivity
import rainbowriches.lucky.start.game.manager.MusicManager
import rainbowriches.lucky.start.game.manager.NavigationManager
import rainbowriches.lucky.start.game.manager.SoundManager
import rainbowriches.lucky.start.game.manager.SpriteManager
import rainbowriches.lucky.start.game.manager.util.MusicUtil
import rainbowriches.lucky.start.game.manager.util.SoundUtil
import rainbowriches.lucky.start.game.manager.util.SpriteUtil
import rainbowriches.lucky.start.game.screens.LoaderScreen
import rainbowriches.lucky.start.game.utils.GameColor
import rainbowriches.lucky.start.game.utils.advanced.AdvancedGame
import rainbowriches.lucky.start.game.utils.disposeAll
import rainbowriches.lucky.start.util.log

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
            disposeAll(musicUtil, assetManager)
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}