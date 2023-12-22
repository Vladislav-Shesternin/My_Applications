package mobile.jogodobicho.lucky.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import mobile.jogodobicho.lucky.MainActivity
import mobile.jogodobicho.lucky.game.manager.MusicManager
import mobile.jogodobicho.lucky.game.manager.NavigationManager
import mobile.jogodobicho.lucky.game.manager.SoundManager
import mobile.jogodobicho.lucky.game.manager.SpriteManager
import mobile.jogodobicho.lucky.game.manager.util.MusicUtil
import mobile.jogodobicho.lucky.game.manager.util.SoundUtil
import mobile.jogodobicho.lucky.game.manager.util.SpriteUtil
import mobile.jogodobicho.lucky.game.screens.BullLoadingScreen
import mobile.jogodobicho.lucky.game.utils.advanced.AdvancedGame
import mobile.jogodobicho.lucky.game.utils.disposeAll
import mobile.jogodobicho.lucky.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    companion object {
        var isMusic = true
    }

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val splashAssets by lazy { SpriteUtil.LoadingAssets() }
    val gameAssets   by lazy { SpriteUtil.GameAssets() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(BullLoadingScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(Color.WHITE)
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