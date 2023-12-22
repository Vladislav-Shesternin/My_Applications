package indiaquiz.winterenter.holiwensday.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import indiaquiz.winterenter.holiwensday.MainActivity
import indiaquiz.winterenter.holiwensday.game.manager.AllAssets
import indiaquiz.winterenter.holiwensday.game.manager.MusicManager
import indiaquiz.winterenter.holiwensday.game.manager.MusicUtil
import indiaquiz.winterenter.holiwensday.game.manager.NavigationManager
import indiaquiz.winterenter.holiwensday.game.manager.SpriteManager
import indiaquiz.winterenter.holiwensday.game.screens.HappySplashScreen
import indiaquiz.winterenter.holiwensday.game.utils.advanced.AdvancedGame
import indiaquiz.winterenter.holiwensday.game.utils.disposeAll
import indiaquiz.winterenter.holiwensday.util.log

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

        navigationManager.navigate(HappySplashScreen::class.java.name)
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