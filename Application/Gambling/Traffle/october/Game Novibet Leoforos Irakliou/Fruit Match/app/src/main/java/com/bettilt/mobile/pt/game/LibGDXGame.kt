package com.bettilt.mobile.pt.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.bettilt.mobile.pt.MainActivity
import com.bettilt.mobile.pt.game.manager.MusicManager
import com.bettilt.mobile.pt.game.manager.NavigationManager
import com.bettilt.mobile.pt.game.manager.SoundManager
import com.bettilt.mobile.pt.game.manager.SpriteManager
import com.bettilt.mobile.pt.game.manager.util.MusicUtil
import com.bettilt.mobile.pt.game.manager.util.SoundUtil
import com.bettilt.mobile.pt.game.manager.util.SpriteUtil
import com.bettilt.mobile.pt.game.screens.LoaderScreen
import com.bettilt.mobile.pt.game.utils.GameColor
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedGame
import com.bettilt.mobile.pt.game.utils.disposeAll
import com.bettilt.mobile.pt.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager private set
    lateinit var musicManager     : MusicManager private set
    lateinit var soundManager     : SoundManager private set

    val musicUtil    by lazy { MusicUtil() }
    val soundUtil    by lazy { SoundUtil() }
    val spriteUtil   by lazy { SpriteUtil.AllAssets() }

    var backgroundColor = GameColor.background
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