package com.sca.rab.que.stgame.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.sca.rab.que.stgame.MainActivity
import com.sca.rab.que.stgame.game.manager.MusicManager
import com.sca.rab.que.stgame.game.manager.NavigationManager
import com.sca.rab.que.stgame.game.manager.SoundManager
import com.sca.rab.que.stgame.game.manager.SpriteManager
import com.sca.rab.que.stgame.game.manager.util.MusicUtil
import com.sca.rab.que.stgame.game.manager.util.SoundUtil
import com.sca.rab.que.stgame.game.manager.util.SpriteUtil
import com.sca.rab.que.stgame.game.screens.LoaLoadScreen
import com.sca.rab.que.stgame.game.utils.advanced.AdvancedGame
import com.sca.rab.que.stgame.game.utils.disposeAll
import com.sca.rab.que.stgame.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager private set
    lateinit var musicManager     : MusicManager private set
    lateinit var soundManager     : SoundManager private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val splashAssets by lazy { SpriteUtil.SplashAssets() }
    val gameAssets   by lazy { SpriteUtil.GameAssets() }

    var backgroundColor = Color.BLACK
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(LoaLoadScreen::class.java.name)
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