package com.tdapps.test.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.tdapps.test.MainActivity
import com.tdapps.test.game.manager.NavigationManager
import com.tdapps.test.game.manager.SpriteManager
import com.tdapps.test.game.manager.util.SpriteUtil
import com.tdapps.test.game.screens.LoaderScreen
import com.tdapps.test.game.utils.GameColor
import com.tdapps.test.game.utils.advanced.AdvancedGame
import com.tdapps.test.game.utils.disposeAll
import com.tdapps.test.util.log

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set

    val spriteUtil       by lazy { SpriteUtil.CommonAssets() }
    val spriteUtilSplash by lazy { SpriteUtil.SplashAssets() }

    var backgroundColor = GameColor.background
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

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
            disposeAll(assetManager)
            super.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}