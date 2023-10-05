package mange.yourse.finnnance.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.ScreenUtils
import mange.yourse.finnnance.game.manager.MusicManager
import mange.yourse.finnnance.game.manager.NavigationManager
import mange.yourse.finnnance.game.manager.SpriteManager
import mange.yourse.finnnance.game.manager.util.MusicUtil
import mange.yourse.finnnance.game.manager.util.SpriteUtil
import mange.yourse.finnnance.game.screens.LoadingScreen
import mange.yourse.finnnance.game.utils.advanced.AdvancedGame
import mange.yourse.finnnance.util.log

class LibGDXGame(val activity: mange.yourse.finnnance.MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
   // lateinit var soundManager     : SoundManager      private set
   // lateinit var fontTTFManager   : FontTTFManager    private set

    val spriteUtil  by lazy { SpriteUtil() }
    val musicUtil   by lazy { MusicUtil() }
   // val soundUtil   by lazy { SoundUtil() }
   // val fontTTFUtil by lazy { FontTTFUtil() }
//    val themeUtil   by lazy { ThemeUtil(spriteUtil) }

    var backgroundColor = Color.WHITE

    override fun create() {
        assetManager      = AssetManager()
        navigationManager = NavigationManager(this)
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
      //  soundManager      = SoundManager(assetManager)
      //  fontTTFManager    = FontTTFManager(assetManager)

        navigationManager.navigate(LoadingScreen(this))
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