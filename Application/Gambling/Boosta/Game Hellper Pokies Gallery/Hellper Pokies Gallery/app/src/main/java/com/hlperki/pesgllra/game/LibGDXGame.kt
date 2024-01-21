package com.hlperki.pesgllra.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.hlperki.pesgllra.MainActivity
import com.hlperki.pesgllra.game.manager.AllAssets
import com.hlperki.pesgllra.game.manager.MusicManager
import com.hlperki.pesgllra.game.manager.MusicUtil
import com.hlperki.pesgllra.game.manager.NavigationManager
import com.hlperki.pesgllra.game.manager.SpriteManager
import com.hlperki.pesgllra.game.screens.HappySplashScreen
import com.hlperki.pesgllra.game.utils.advanced.AdvancedGame
import com.hlperki.pesgllra.game.utils.disposeAll
import com.hlperki.pesgllra.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set

    val assets       by lazy { AllAssets() }
    val musicUtil    by lazy { MusicUtil() }

    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)

        navigationManager.navigate(HappySplashScreen::class.java.name)
    }

    override fun render() {
        ScreenUtils.clear(Color.WHITE)
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