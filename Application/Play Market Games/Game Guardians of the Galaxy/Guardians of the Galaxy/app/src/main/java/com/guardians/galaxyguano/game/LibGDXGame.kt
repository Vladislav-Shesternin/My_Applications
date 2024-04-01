package com.guardians.galaxyguano.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.guardians.galaxyguano.MainActivity
import com.guardians.galaxyguano.game.manager.MusicManager
import com.guardians.galaxyguano.game.manager.NavigationManager
import com.guardians.galaxyguano.game.manager.ParticleEffectManager
import com.guardians.galaxyguano.game.manager.SoundManager
import com.guardians.galaxyguano.game.manager.SpriteManager
import com.guardians.galaxyguano.game.manager.util.MusicUtil
import com.guardians.galaxyguano.game.manager.util.ParticleEffectUtil
import com.guardians.galaxyguano.game.manager.util.SoundUtil
import com.guardians.galaxyguano.game.manager.util.SpriteUtil
import com.guardians.galaxyguano.game.screens.GalaxyLoaderScreen
import com.guardians.galaxyguano.game.utils.advanced.AdvancedGame
import com.guardians.galaxyguano.game.utils.disposeAll
import com.guardians.galaxyguano.util.log

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

        navigationManager.navigate(GalaxyLoaderScreen::class.java.name)
    }

    private val colorBackground = Color.WHITE

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