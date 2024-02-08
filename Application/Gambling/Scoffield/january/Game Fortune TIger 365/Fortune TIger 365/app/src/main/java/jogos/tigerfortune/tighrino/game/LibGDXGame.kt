package jogos.tigerfortune.tighrino.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import jogos.tigerfortune.tighrino.MainActivity
import jogos.tigerfortune.tighrino.game.manager.MusicManager
import jogos.tigerfortune.tighrino.game.manager.NavigationManager
import jogos.tigerfortune.tighrino.game.manager.SoundManager
import jogos.tigerfortune.tighrino.game.manager.SpriteManager
import jogos.tigerfortune.tighrino.game.manager.util.MusicUtil
import jogos.tigerfortune.tighrino.game.manager.util.SoundUtil
import jogos.tigerfortune.tighrino.game.manager.util.SpriteUtil
import jogos.tigerfortune.tighrino.game.screens.TLoaderScreen
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedGame
import jogos.tigerfortune.tighrino.game.utils.disposeAll
import jogos.tigerfortune.tighrino.util.log

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