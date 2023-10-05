package crupp.tsk.learnn.inggg.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import crupp.tsk.learnn.inggg.game.manager.FontTTFManager
import crupp.tsk.learnn.inggg.game.manager.NavigationManager
import crupp.tsk.learnn.inggg.game.manager.SpriteManager
import crupp.tsk.learnn.inggg.game.manager.util.FontTTFUtil
import crupp.tsk.learnn.inggg.game.manager.util.SpriteUtil
import crupp.tsk.learnn.inggg.game.screens.MasloScreen
import crupp.tsk.learnn.inggg.game.utils.GameColor
import crupp.tsk.learnn.inggg.game.utils.advanced.AdvancedGame
import crupp.tsk.learnn.inggg.util.log

class LibGDXGame(val activity: crupp.tsk.learnn.inggg.MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    //lateinit var musicManager     : MusicManager      private set
   // lateinit var soundManager     : SoundManager      private set
    lateinit var fontTTFManager   : FontTTFManager private set

    val spriteUtil  by lazy { SpriteUtil() }
    //val musicUtil   by lazy { MusicUtil() }
   // val soundUtil   by lazy { SoundUtil() }
    val fontTTFUtil by lazy { FontTTFUtil() }
//    val themeUtil   by lazy { ThemeUtil(spriteUtil) }

    var backgroundColor = GameColor.background

    override fun create() {
        assetManager      = AssetManager()
        navigationManager = NavigationManager(this)
        spriteManager     = SpriteManager(assetManager)
        //musicManager      = MusicManager(assetManager)
      //  soundManager      = SoundManager(assetManager)
        fontTTFManager    = FontTTFManager(assetManager)

        navigationManager.navigate(MasloScreen(this))
    }

    override fun render() {
        ScreenUtils.clear(backgroundColor)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            super.dispose()
            //musicUtil.dispose()
            assetManager.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}