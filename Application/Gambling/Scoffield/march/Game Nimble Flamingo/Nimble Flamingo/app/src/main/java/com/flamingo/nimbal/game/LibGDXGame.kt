package com.flamingo.nimbal.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.flamingo.nimbal.MainActivity
import com.flamingo.nimbal.game.manager.MusicManager
import com.flamingo.nimbal.game.manager.NavigationManager
import com.flamingo.nimbal.game.manager.SoundManager
import com.flamingo.nimbal.game.manager.SpriteManager
import com.flamingo.nimbal.game.manager.util.MusicUtil
import com.flamingo.nimbal.game.manager.util.SoundUtil
import com.flamingo.nimbal.game.manager.util.SpriteUtil
import com.flamingo.nimbal.game.screens.FlamingoLoaderScreen
import com.flamingo.nimbal.game.utils.advanced.AdvancedGame
import com.flamingo.nimbal.game.utils.disposeAll
import com.flamingo.nimbal.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val allAssets    by lazy { SpriteUtil.AllAssets() }
    val startAssets  by lazy { SpriteUtil.StartAssets() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(FlamingoLoaderScreen::class.java.name)
    }

    private val colorBackground = Color.valueOf("526112")

    override fun render() {
        ScreenUtils.clear(colorBackground)
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