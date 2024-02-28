package com.funsagon.hexagon.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.funsagon.hexagon.MainActivity
import com.funsagon.hexagon.game.manager.MusicManager
import com.funsagon.hexagon.game.manager.NavigationManager
import com.funsagon.hexagon.game.manager.SoundManager
import com.funsagon.hexagon.game.manager.SpriteManager
import com.funsagon.hexagon.game.manager.util.MusicUtil
import com.funsagon.hexagon.game.manager.util.SoundUtil
import com.funsagon.hexagon.game.manager.util.SpriteUtil
import com.funsagon.hexagon.game.screens.HexagonLoaderScreen
import com.funsagon.hexagon.game.utils.advanced.AdvancedGame
import com.funsagon.hexagon.game.utils.disposeAll
import com.funsagon.hexagon.util.log

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

        navigationManager.navigate(HexagonLoaderScreen::class.java.name)
    }

    private val colorBackground = Color.valueOf("A8D05D")

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