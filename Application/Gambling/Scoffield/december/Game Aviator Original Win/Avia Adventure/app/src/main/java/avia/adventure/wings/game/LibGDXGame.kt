package avia.adventure.wings.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import avia.adventure.wings.MainActivity
import avia.adventure.wings.game.manager.MusicManager
import avia.adventure.wings.game.manager.NavigationManager
import avia.adventure.wings.game.manager.SoundManager
import avia.adventure.wings.game.manager.SpriteManager
import avia.adventure.wings.game.manager.util.MusicUtil
import avia.adventure.wings.game.manager.util.SoundUtil
import avia.adventure.wings.game.manager.util.SpriteUtil
import avia.adventure.wings.game.screens.OriginalLoadingScreen
import avia.adventure.wings.game.utils.advanced.AdvancedGame
import avia.adventure.wings.game.utils.disposeAll
import avia.adventure.wings.util.log

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

        navigationManager.navigate(OriginalLoadingScreen::class.java.name)
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