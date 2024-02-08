package com.ottplay.ottpl.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.ottplay.ottpl.MainActivity
import com.ottplay.ottpl.game.manager.MusicManager
import com.ottplay.ottpl.game.manager.NavigationManager
import com.ottplay.ottpl.game.manager.SoundManager
import com.ottplay.ottpl.game.manager.SpriteManager
import com.ottplay.ottpl.game.manager.util.MusicUtil
import com.ottplay.ottpl.game.manager.util.SoundUtil
import com.ottplay.ottpl.game.manager.util.SpriteUtil
import com.ottplay.ottpl.game.screens.LoaderScreen
import com.ottplay.ottpl.game.utils.advanced.AdvancedGame
import com.ottplay.ottpl.game.utils.disposeAll
import com.ottplay.ottpl.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val loaderAssets by lazy { SpriteUtil.LoaderAssets() }
    val allAssets    by lazy { SpriteUtil.AllAssets() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(LoaderScreen::class.java.name)
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