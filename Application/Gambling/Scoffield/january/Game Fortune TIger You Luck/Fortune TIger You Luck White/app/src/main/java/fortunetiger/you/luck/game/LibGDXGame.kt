package fortunetiger.you.luck.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import fortunetiger.you.luck.MainActivity
import fortunetiger.you.luck.game.manager.MusicManager
import fortunetiger.you.luck.game.manager.NavigationManager
import fortunetiger.you.luck.game.manager.SoundManager
import fortunetiger.you.luck.game.manager.SpriteManager
import fortunetiger.you.luck.game.manager.util.MusicUtil
import fortunetiger.you.luck.game.manager.util.SoundUtil
import fortunetiger.you.luck.game.manager.util.SpriteUtil
import fortunetiger.you.luck.game.screens.LoaLoadScreen
import fortunetiger.you.luck.game.utils.advanced.AdvancedGame
import fortunetiger.you.luck.game.utils.disposeAll
import fortunetiger.you.luck.util.log

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

        navigationManager.navigate(LoaLoadScreen::class.java.name)
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