package com.egypt.tian.sto.game.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.egypt.tian.sto.game.MainActivity
import com.egypt.tian.sto.game.game.manager.MusicManager
import com.egypt.tian.sto.game.game.manager.NavigationManager
import com.egypt.tian.sto.game.game.manager.SoundManager
import com.egypt.tian.sto.game.game.manager.SpriteManager
import com.egypt.tian.sto.game.game.manager.util.MusicUtil
import com.egypt.tian.sto.game.game.manager.util.SoundUtil
import com.egypt.tian.sto.game.game.manager.util.SpriteUtil
import com.egypt.tian.sto.game.game.screens.LoadScreen
import com.egypt.tian.sto.game.game.utils.advanced.AdvancedGame
import com.egypt.tian.sto.game.game.utils.disposeAll
import com.egypt.tian.sto.game.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager private set
    lateinit var musicManager     : MusicManager private set
    lateinit var soundManager     : SoundManager private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val loaderAssets by lazy { SpriteUtil.LoaderAssets() }
    val allAssets    by lazy { SpriteUtil.AllAssets()  }

    var backgroundColor = Color.BLACK
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(LoadScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(backgroundColor)
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