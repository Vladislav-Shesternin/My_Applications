package seville.vontecarlo.chocolatequiz.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import seville.vontecarlo.chocolatequiz.MainActivity
import seville.vontecarlo.chocolatequiz.game.manager.AllAssets
import seville.vontecarlo.chocolatequiz.game.manager.MusicManager
import seville.vontecarlo.chocolatequiz.game.manager.MusicUtil
import seville.vontecarlo.chocolatequiz.game.manager.NavigationManager
import seville.vontecarlo.chocolatequiz.game.manager.SpriteManager
import seville.vontecarlo.chocolatequiz.game.screens.WonkaSplashScreen
import seville.vontecarlo.chocolatequiz.game.utils.advanced.AdvancedGame
import seville.vontecarlo.chocolatequiz.game.utils.disposeAll
import seville.vontecarlo.chocolatequiz.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set

    val assets       by lazy { AllAssets() }
    val musicUtil    by lazy { MusicUtil() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)

        navigationManager.navigate(WonkaSplashScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(Color.WHITE)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            disposableSet.disposeAll()
            disposeAll(assetManager, musicUtil)
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}