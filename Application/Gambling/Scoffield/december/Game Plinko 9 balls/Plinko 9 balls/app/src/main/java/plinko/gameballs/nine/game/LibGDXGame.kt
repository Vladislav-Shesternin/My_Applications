package plinko.gameballs.nine.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import plinko.gameballs.nine.MainActivity
import plinko.gameballs.nine.game.manager.MusicManager
import plinko.gameballs.nine.game.manager.NavigationManager
import plinko.gameballs.nine.game.manager.SoundManager
import plinko.gameballs.nine.game.manager.SpriteManager
import plinko.gameballs.nine.game.manager.util.MusicUtil
import plinko.gameballs.nine.game.manager.util.SoundUtil
import plinko.gameballs.nine.game.manager.util.SpriteUtil
import plinko.gameballs.nine.game.screens.RedBallLoadingScreen
import plinko.gameballs.nine.game.utils.advanced.AdvancedGame
import plinko.gameballs.nine.game.utils.disposeAll
import plinko.gameballs.nine.util.log

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

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(RedBallLoadingScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(Color.BLACK)
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