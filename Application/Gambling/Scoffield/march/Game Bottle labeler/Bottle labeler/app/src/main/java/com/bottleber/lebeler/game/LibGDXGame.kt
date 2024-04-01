package com.bottleber.lebeler.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.bottleber.lebeler.MainActivity
import com.bottleber.lebeler.game.manager.MusicManager
import com.bottleber.lebeler.game.manager.NavigationManager
import com.bottleber.lebeler.game.manager.ParticleEffectManager
import com.bottleber.lebeler.game.manager.SoundManager
import com.bottleber.lebeler.game.manager.SpriteManager
import com.bottleber.lebeler.game.manager.util.MusicUtil
import com.bottleber.lebeler.game.manager.util.ParticleEffectUtil
import com.bottleber.lebeler.game.manager.util.SoundUtil
import com.bottleber.lebeler.game.manager.util.SpriteUtil
import com.bottleber.lebeler.game.screens.BotlerLoaderScreen
import com.bottleber.lebeler.game.utils.advanced.AdvancedGame
import com.bottleber.lebeler.game.utils.disposeAll
import com.bottleber.lebeler.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set
    lateinit var particleEffectManager: ParticleEffectManager private set


    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val allAssets    by lazy { SpriteUtil.AllAssets() }
    val loaderAssets by lazy { SpriteUtil.LoaderAssets() }
    val particleEffectUtil by lazy { ParticleEffectUtil() }


    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)
        particleEffectManager = ParticleEffectManager(assetManager)

        navigationManager.navigate(BotlerLoaderScreen::class.java.name)
    }

    private val colorBackground = Color.BLACK

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