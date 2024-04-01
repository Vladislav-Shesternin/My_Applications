package com.winterria.jumpilo.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.winterria.jumpilo.MainActivity
import com.winterria.jumpilo.game.manager.MusicManager
import com.winterria.jumpilo.game.manager.NavigationManager
import com.winterria.jumpilo.game.manager.SoundManager
import com.winterria.jumpilo.game.manager.SpriteManager
import com.winterria.jumpilo.game.manager.util.MusicUtil
import com.winterria.jumpilo.game.manager.util.SoundUtil
import com.winterria.jumpilo.game.manager.util.SpriteUtil
import com.winterria.jumpilo.game.screens.LoaderScreen
import com.winterria.jumpilo.game.utils.advanced.AdvancedGame
import com.winterria.jumpilo.game.utils.disposeAll
import com.winterria.jumpilo.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val allAssets    by lazy { SpriteUtil.AllAssets() }
    val loaderAssets by lazy { SpriteUtil.LoaderAssets() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(LoaderScreen::class.java.name)
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