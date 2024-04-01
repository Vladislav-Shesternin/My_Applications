package com.phara.ohegy.ptgame.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.phara.ohegy.ptgame.MainActivity
import com.phara.ohegy.ptgame.game.manager.MusicManager
import com.phara.ohegy.ptgame.game.manager.NavigationManager
import com.phara.ohegy.ptgame.game.manager.SoundManager
import com.phara.ohegy.ptgame.game.manager.SpriteManager
import com.phara.ohegy.ptgame.game.manager.util.MusicUtil
import com.phara.ohegy.ptgame.game.manager.util.SoundUtil
import com.phara.ohegy.ptgame.game.manager.util.SpriteUtil
import com.phara.ohegy.ptgame.game.screens.LoaderScreen
import com.phara.ohegy.ptgame.game.utils.advanced.AdvancedGame
import com.phara.ohegy.ptgame.game.utils.disposeAll
import com.phara.ohegy.ptgame.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val musicUtil     by lazy { MusicUtil()    }
    val soundUtil     by lazy { SoundUtil()    }
    val loaderAssets  by lazy { SpriteUtil.LoaderAssets() }
    val allAssets     by lazy { SpriteUtil.Assets() }

    var backgroundColor = Color.BLACK
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(LoaderScreen::class.java.name)
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