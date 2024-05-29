package com.rusya.cartycoo.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.rusya.cartycoo.game.manager.*
import com.rusya.cartycoo.game.manager.util.MusicUtil
import com.rusya.cartycoo.game.manager.util.ParticleEffectUtil
import com.rusya.cartycoo.game.manager.util.SoundUtil
import com.rusya.cartycoo.game.manager.util.SpriteUtil
import com.rusya.cartycoo.game.screens.LodrinkingScreen
import com.rusya.cartycoo.game.utils.PoromColor
import com.rusya.cartycoo.game.utils.advanced.AdvancedGame
import com.rusya.cartycoo.game.utils.disposeAll
import com.rusya.cartycoo.util.log

class PoromGame(val activity: com.rusya.cartycoo.MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set
    lateinit var particleEffectManager: ParticleEffectManager private set

    val faradeo by lazy { SpriteUtil.Faradeo() }
    val guglas  by lazy { SpriteUtil.Guglas() }

    val musicUtil          by lazy { MusicUtil()    }
    val soundUtil          by lazy { SoundUtil()    }
    val particleEffectUtil by lazy { ParticleEffectUtil() }


    var backgroundColor = PoromColor.saburno
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        particleEffectManager = ParticleEffectManager(assetManager)

        navigationManager.navigate(LodrinkingScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(backgroundColor)
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