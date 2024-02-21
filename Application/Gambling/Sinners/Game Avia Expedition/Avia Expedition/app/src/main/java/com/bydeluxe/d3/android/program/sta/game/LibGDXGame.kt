package com.bydeluxe.d3.android.program.sta.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.bydeluxe.d3.android.program.sta.MainActivity
import com.bydeluxe.d3.android.program.sta.game.manager.MusicManager
import com.bydeluxe.d3.android.program.sta.game.manager.NavigationManager
import com.bydeluxe.d3.android.program.sta.game.manager.SoundManager
import com.bydeluxe.d3.android.program.sta.game.manager.SpriteManager
import com.bydeluxe.d3.android.program.sta.game.manager.util.MusicUtil
import com.bydeluxe.d3.android.program.sta.game.manager.util.SoundUtil
import com.bydeluxe.d3.android.program.sta.game.manager.util.SpriteUtil
import com.bydeluxe.d3.android.program.sta.game.screens.LoadScreen
import com.bydeluxe.d3.android.program.sta.game.utils.advanced.AdvancedGame
import com.bydeluxe.d3.android.program.sta.game.utils.disposeAll
import com.bydeluxe.d3.android.program.sta.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val loadAssets   by lazy { SpriteUtil.LoadAssets() }
    val allAssets    by lazy { SpriteUtil.AllAssets() }

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