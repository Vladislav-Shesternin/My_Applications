package plinko.games.mega.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import plinko.games.mega.MainActivity
import plinko.games.mega.game.manager.MusicManager
import plinko.games.mega.game.manager.NavigationManager
import plinko.games.mega.game.manager.SoundManager
import plinko.games.mega.game.manager.SpriteManager
import plinko.games.mega.game.manager.util.MusicUtil
import plinko.games.mega.game.manager.util.SoundUtil
import plinko.games.mega.game.manager.util.SpriteUtil
import plinko.games.mega.game.screens.LoaderScreen
import plinko.games.mega.game.utils.GameColor
import plinko.games.mega.game.utils.advanced.AdvancedGame
import plinko.games.mega.game.utils.disposeAll
import plinko.games.mega.util.log

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