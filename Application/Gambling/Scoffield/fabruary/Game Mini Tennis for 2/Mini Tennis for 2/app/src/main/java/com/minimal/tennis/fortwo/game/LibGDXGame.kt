package com.minimal.tennis.fortwo.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.minimal.tennis.fortwo.MainActivity
import com.minimal.tennis.fortwo.game.manager.MusicManager
import com.minimal.tennis.fortwo.game.manager.NavigationManager
import com.minimal.tennis.fortwo.game.manager.SoundManager
import com.minimal.tennis.fortwo.game.manager.SpriteManager
import com.minimal.tennis.fortwo.game.manager.util.MusicUtil
import com.minimal.tennis.fortwo.game.manager.util.SoundUtil
import com.minimal.tennis.fortwo.game.manager.util.SpriteUtil
import com.minimal.tennis.fortwo.game.screens.LoaderScreen
import com.minimal.tennis.fortwo.game.utils.advanced.AdvancedGame
import com.minimal.tennis.fortwo.game.utils.disposeAll
import com.minimal.tennis.fortwo.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
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

    private val colorBackground = Color.valueOf("22262A")

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