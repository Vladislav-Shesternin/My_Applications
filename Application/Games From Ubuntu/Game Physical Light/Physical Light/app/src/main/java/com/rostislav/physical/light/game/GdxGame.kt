package com.rostislav.physical.light.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.rostislav.physical.light.game.manager.NavigationManager
import com.rostislav.physical.light.game.manager.SpriteManager
import com.rostislav.physical.light.game.manager.util.SpriteUtil
import com.rostislav.physical.light.game.screens.LoaderScreen
import com.rostislav.physical.light.game.utils.GdxColor
import com.rostislav.physical.light.game.utils.advanced.AdvancedGame
import com.rostislav.physical.light.game.utils.disposeAll
import com.rostislav.physical.light.util.log

class GdxGame(val activity: com.rostislav.physical.light.MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set

    val assets by lazy { SpriteUtil.AllAssets() }

    var backgroundColor = GdxColor.dark
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