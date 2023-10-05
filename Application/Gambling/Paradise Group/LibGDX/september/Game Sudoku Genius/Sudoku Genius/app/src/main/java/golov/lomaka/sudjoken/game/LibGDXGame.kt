package golov.lomaka.sudjoken.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import golov.lomaka.sudjoken.game.manager.FontTTFManager
import golov.lomaka.sudjoken.game.manager.MusicManager
import golov.lomaka.sudjoken.game.manager.NavigationManager
import golov.lomaka.sudjoken.game.manager.SpriteManager
import golov.lomaka.sudjoken.game.manager.util.FontTTFUtil
import golov.lomaka.sudjoken.game.manager.util.MusicUtil
import golov.lomaka.sudjoken.game.manager.util.SpriteUtil
import golov.lomaka.sudjoken.game.screens.SapranoScreen
import golov.lomaka.sudjoken.game.utils.GameColor
import golov.lomaka.sudjoken.game.utils.advanced.AdvancedGame
import golov.lomaka.sudjoken.util.log

class LibGDXGame(val activity: golov.lomaka.sudjoken.MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
   // lateinit var soundManager     : SoundManager      private set
    lateinit var fontTTFManager   : FontTTFManager private set

    val spriteUtil  by lazy { SpriteUtil() }
    val musicUtil   by lazy { MusicUtil() }
   // val soundUtil   by lazy { SoundUtil() }
    val fontTTFUtil by lazy { FontTTFUtil() }
//    val themeUtil   by lazy { ThemeUtil(spriteUtil) }

    var backgroundColor = GameColor.background
    var isWhite = true

    override fun create() {
        assetManager      = AssetManager()
        navigationManager = NavigationManager(this)
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
      //  soundManager      = SoundManager(assetManager)
        fontTTFManager    = FontTTFManager(assetManager)

        navigationManager.navigate(SapranoScreen(this))
    }

    override fun render() {
        ScreenUtils.clear(backgroundColor)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            super.dispose()
            musicUtil.dispose()
            assetManager.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}