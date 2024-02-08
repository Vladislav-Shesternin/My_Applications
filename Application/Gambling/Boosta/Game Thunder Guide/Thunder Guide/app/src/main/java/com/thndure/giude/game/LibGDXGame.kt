package com.thndure.giude.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.thndure.giude.game.manager.NavigationManager
import com.thndure.giude.game.manager.SpriteManager
import com.thndure.giude.game.manager.util.SpriteUtil
import com.thndure.giude.game.screens.LoaderScreen
import com.thndure.giude.game.utils.GameColor
import com.thndure.giude.game.utils.advanced.AdvancedGame
import com.thndure.giude.game.utils.disposeAll
import com.thndure.giude.log

class LibGDXGame(val activity: com.thndure.giude.MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set

    val spriteUtil by lazy { SpriteUtil.CommonAssets() }

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