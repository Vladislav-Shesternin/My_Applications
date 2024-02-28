package com.levitation.plates.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.levitation.plates.MainActivity
import com.levitation.plates.game.manager.MusicManager
import com.levitation.plates.game.manager.NavigationManager
import com.levitation.plates.game.manager.SoundManager
import com.levitation.plates.game.manager.SpriteManager
import com.levitation.plates.game.manager.util.MusicUtil
import com.levitation.plates.game.manager.util.SoundUtil
import com.levitation.plates.game.manager.util.SpriteUtil
import com.levitation.plates.game.screens.LoaderScreen
import com.levitation.plates.game.utils.advanced.AdvancedGame
import com.levitation.plates.game.utils.disposeAll
import com.levitation.plates.util.log

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

    private val colorBackground = Color.valueOf("040845")

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