package notconvert.notvalue.notvista.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import notconvert.notvalue.notvista.game.manager.FontTTFManager
import notconvert.notvalue.notvista.game.manager.MusicManager
import notconvert.notvalue.notvista.game.manager.NavigationManager
import notconvert.notvalue.notvista.game.manager.SpriteManager
import notconvert.notvalue.notvista.game.manager.util.FontTTFUtil
import notconvert.notvalue.notvista.game.manager.util.MusicUtil
import notconvert.notvalue.notvista.game.manager.util.SpriteUtil
import notconvert.notvalue.notvista.game.screens.SadamScreen
import notconvert.notvalue.notvista.game.utils.GameColor
import notconvert.notvalue.notvista.game.utils.advanced.AdvancedGame
import notconvert.notvalue.notvista.util.log

class LibGDXGame(val activity: notconvert.notvalue.notvista.MainActivity) : AdvancedGame() {

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

    override fun create() {
        assetManager      = AssetManager()
        navigationManager = NavigationManager(this)
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
      //  soundManager      = SoundManager(assetManager)
        fontTTFManager    = FontTTFManager(assetManager)

        navigationManager.navigate(SadamScreen(this))
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