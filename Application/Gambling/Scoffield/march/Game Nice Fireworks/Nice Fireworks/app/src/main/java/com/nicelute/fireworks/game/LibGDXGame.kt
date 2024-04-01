package com.nicelute.fireworks.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.nicelute.fireworks.MainActivity
import com.nicelute.fireworks.game.manager.MusicManager
import com.nicelute.fireworks.game.manager.NavigationManager
import com.nicelute.fireworks.game.manager.ParticleEffectManager
import com.nicelute.fireworks.game.manager.SoundManager
import com.nicelute.fireworks.game.manager.SpriteManager
import com.nicelute.fireworks.game.manager.util.MusicUtil
import com.nicelute.fireworks.game.manager.util.ParticleEffectUtil
import com.nicelute.fireworks.game.manager.util.SoundUtil
import com.nicelute.fireworks.game.manager.util.SpriteUtil
import com.nicelute.fireworks.game.screens.NiceLoaderScreen
import com.nicelute.fireworks.game.utils.advanced.AdvancedGame
import com.nicelute.fireworks.game.utils.disposeAll
import com.nicelute.fireworks.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager      : AssetManager      private set
    lateinit var navigationManager : NavigationManager private set
    lateinit var spriteManager     : SpriteManager     private set
    lateinit var musicManager      : MusicManager      private set
    lateinit var soundManager      : SoundManager      private set
    lateinit var particleEffectManager: ParticleEffectManager private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val allAssets    by lazy { SpriteUtil.AllAssets() }
    val particleEffectUtil by lazy { ParticleEffectUtil() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)
        particleEffectManager = ParticleEffectManager(assetManager)

        navigationManager.navigate(NiceLoaderScreen::class.java.name)
    }

    private val colorBackground = Color.valueOf("171A3B")

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