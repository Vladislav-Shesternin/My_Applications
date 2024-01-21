package jogo.fortunetiger.tighrino.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import jogo.fortunetiger.tighrino.MainActivity
import jogo.fortunetiger.tighrino.game.manager.MusicManager
import jogo.fortunetiger.tighrino.game.manager.NavigationManager
import jogo.fortunetiger.tighrino.game.manager.SoundManager
import jogo.fortunetiger.tighrino.game.manager.SpriteManager
import jogo.fortunetiger.tighrino.game.manager.util.MusicUtil
import jogo.fortunetiger.tighrino.game.manager.util.SoundUtil
import jogo.fortunetiger.tighrino.game.manager.util.SpriteUtil
import jogo.fortunetiger.tighrino.game.screens.TLoaderScreen
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedGame
import jogo.fortunetiger.tighrino.game.utils.disposeAll
import jogo.fortunetiger.tighrino.util.log

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

        navigationManager.navigate(TLoaderScreen::class.java.name)
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