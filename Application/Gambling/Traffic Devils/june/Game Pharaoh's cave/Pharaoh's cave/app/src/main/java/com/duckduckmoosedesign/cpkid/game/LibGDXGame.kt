package com.duckduckmoosedesign.cpkid.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.duckduckmoosedesign.cpkid.GameActivity
import com.duckduckmoosedesign.cpkid.game.manager.*
import com.duckduckmoosedesign.cpkid.game.manager.util.MusicUtil
import com.duckduckmoosedesign.cpkid.game.manager.util.SoundUtil
import com.duckduckmoosedesign.cpkid.game.manager.util.SpriteUtil
import com.duckduckmoosedesign.cpkid.game.screens.LoadingScreen
import com.duckduckmoosedesign.cpkid.game.utils.GColor
import com.duckduckmoosedesign.cpkid.game.utils.advanced.AdvancedGame
import com.duckduckmoosedesign.cpkid.game.utils.disposeAll
import com.duckduckmoosedesign.cpkid.util.log

class LibGDXGame(val activity: GameActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val allAss  by lazy { SpriteUtil.All() }
    val loadAss by lazy { SpriteUtil.Loading() }

    val musicUtil by lazy { MusicUtil() }
    val soundUtil by lazy { SoundUtil() }

    var backgroundColor = GColor.background
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(LoadingScreen::class.java.name)
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