package com.goplaytoday.guildofhero.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.goplaytoday.guildofhero.MainActivity
import com.goplaytoday.guildofhero.game.manager.NavigationManager
import com.goplaytoday.guildofhero.game.manager.SpriteManager
import com.goplaytoday.guildofhero.game.manager.util.SpriteUtil
import com.goplaytoday.guildofhero.game.screens.LoaderScreen
import com.goplaytoday.guildofhero.game.utils.GameColor
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedGame
import com.goplaytoday.guildofhero.game.utils.disposeAll
import com.goplaytoday.guildofhero.util.log

class LGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set

    val loaderAssets by lazy { SpriteUtil.LoaderAssets() }
    val commonAssets by lazy { SpriteUtil.CommonAssets() }

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