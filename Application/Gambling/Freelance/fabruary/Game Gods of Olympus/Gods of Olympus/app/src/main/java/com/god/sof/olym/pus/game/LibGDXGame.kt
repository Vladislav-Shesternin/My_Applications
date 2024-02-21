package com.god.sof.olym.pus.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.god.sof.olym.pus.MainActivity
import com.god.sof.olym.pus.game.manager.MusicManager
import com.god.sof.olym.pus.game.manager.NavigationManager
import com.god.sof.olym.pus.game.manager.SoundManager
import com.god.sof.olym.pus.game.manager.SpriteManager
import com.god.sof.olym.pus.game.manager.util.MusicUtil
import com.god.sof.olym.pus.game.manager.util.SoundUtil
import com.god.sof.olym.pus.game.manager.util.SpriteUtil
import com.god.sof.olym.pus.game.screens.OlyLoadingScreen
import com.god.sof.olym.pus.game.utils.advanced.AdvancedGame
import com.god.sof.olym.pus.game.utils.disposeAll
import com.god.sof.olym.pus.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val musicUtil     by lazy { MusicUtil()    }
    val soundUtil     by lazy { SoundUtil()    }
    val loadingAssets by lazy { SpriteUtil.LoadingAssets() }
    val allAssets     by lazy { SpriteUtil.AllAssets() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(OlyLoadingScreen::class.java.name)
    }

    private val backich = Color.valueOf("9F69B4")

    override fun render() {
        ScreenUtils.clear(backich)
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