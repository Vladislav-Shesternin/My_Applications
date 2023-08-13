package com.veldan.lbjt.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.utils.ScreenUtils
import com.veldan.lbjt.MainActivity
import com.veldan.lbjt.game.manager.FontTTFManager
import com.veldan.lbjt.game.manager.MusicManager
import com.veldan.lbjt.game.manager.NavigationManager
import com.veldan.lbjt.game.manager.SoundManager
import com.veldan.lbjt.game.manager.SpriteManager
import com.veldan.lbjt.game.screens.SplashScreen
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.advanced.AdvancedGame
import com.veldan.lbjt.game.manager.util.MusicUtil
import com.veldan.lbjt.game.manager.util.SoundUtil
import com.veldan.lbjt.game.manager.util.SpriteUtil
import com.veldan.lbjt.game.manager.util.FontTTFUtil
import com.veldan.lbjt.game.utils.LanguageUtil
import com.veldan.lbjt.game.utils.ShapeDrawerUtil
import com.veldan.lbjt.game.utils.ThemeUtil
import com.veldan.lbjt.util.log
import java.lang.Exception

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var assetManagerLocal: AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set
    lateinit var fontTTFManager   : FontTTFManager    private set

    val spriteUtil   by lazy { SpriteUtil() }
    val musicUtil    by lazy { MusicUtil() }
    val soundUtil    by lazy { SoundUtil() }
    val fontTTFUtil  by lazy { FontTTFUtil() }
    val themeUtil    by lazy { ThemeUtil(spriteUtil) }
    val languageUtil by lazy { LanguageUtil(activity) }

    var backgroundColor = GameColor.background

    override fun create() {
        assetManager      = AssetManager()
        assetManagerLocal = AssetManager(LocalFileHandleResolver())
        navigationManager = NavigationManager(this)
        spriteManager     = SpriteManager(assetManager)
        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)
        fontTTFManager    = FontTTFManager(assetManager)

        navigationManager.navigate(SplashScreen(this))
    }

    override fun render() {
        ScreenUtils.clear(backgroundColor)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            super.dispose()
            musicUtil.dispose()
            assetManager.dispose()
            assetManagerLocal.dispose()
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}