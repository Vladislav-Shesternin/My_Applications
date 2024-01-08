package aviator.original.fly.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import aviator.original.fly.MainActivity
import aviator.original.fly.game.manager.MusicManager
import aviator.original.fly.game.manager.NavigationManager
import aviator.original.fly.game.manager.SoundManager
import aviator.original.fly.game.manager.SpriteManager
import aviator.original.fly.game.manager.util.MusicUtil
import aviator.original.fly.game.manager.util.SoundUtil
import aviator.original.fly.game.manager.util.SpriteUtil
import aviator.original.fly.game.screens.AviatorLoadingScreen
import aviator.original.fly.game.utils.advanced.AdvancedGame
import aviator.original.fly.game.utils.disposeAll
import aviator.original.fly.util.log

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

    var backgroundColor = Color.valueOf("8B093A")
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(AviatorLoadingScreen::class.java.name)
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