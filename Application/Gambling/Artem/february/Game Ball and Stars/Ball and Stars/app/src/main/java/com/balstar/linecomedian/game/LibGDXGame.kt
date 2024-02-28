package com.balstar.linecomedian.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.balstar.linecomedian.MainActivity
import com.balstar.linecomedian.game.manager.MusicManager
import com.balstar.linecomedian.game.manager.NavigationManager
import com.balstar.linecomedian.game.manager.SoundManager
import com.balstar.linecomedian.game.manager.SpriteManager
import com.balstar.linecomedian.game.manager.util.MusicUtil
import com.balstar.linecomedian.game.manager.util.SoundUtil
import com.balstar.linecomedian.game.manager.util.SpriteUtil
import com.balstar.linecomedian.game.screens.PinkLoaderScreen
import com.balstar.linecomedian.game.utils.advanced.AdvancedGame
import com.balstar.linecomedian.game.utils.disposeAll
import com.balstar.linecomedian.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val musicUtil    by lazy { MusicUtil()    }
    val soundUtil    by lazy { SoundUtil()    }
    val allAssets    by lazy { SpriteUtil.AllAssets() }
    val startAssets  by lazy { SpriteUtil.StartAssets() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(PinkLoaderScreen::class.java.name)
    }

    private val colorBackground = Color.valueOf("FEA3A9")

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