package com.zeuse.zeusjackpotjamboree.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver
import com.badlogic.gdx.utils.ScreenUtils
import com.zeuse.zeusjackpotjamboree.MainActivity
import com.zeuse.zeusjackpotjamboree.game.manager.FontTTFManager
import com.zeuse.zeusjackpotjamboree.game.manager.MusicManager
import com.zeuse.zeusjackpotjamboree.game.manager.NavigationManager
import com.zeuse.zeusjackpotjamboree.game.manager.SoundManager
import com.zeuse.zeusjackpotjamboree.game.manager.SpriteManager
import com.zeuse.zeusjackpotjamboree.game.screens.SplashScreen
import com.zeuse.zeusjackpotjamboree.game.utils.GameColor
import com.zeuse.zeusjackpotjamboree.game.utils.advanced.AdvancedGame
import com.zeuse.zeusjackpotjamboree.game.manager.util.MusicUtil
import com.zeuse.zeusjackpotjamboree.game.manager.util.SoundUtil
import com.zeuse.zeusjackpotjamboree.game.manager.util.SpriteUtil
import com.zeuse.zeusjackpotjamboree.game.manager.util.FontTTFUtil
import com.zeuse.zeusjackpotjamboree.game.utils.ThemeUtil
import com.zeuse.zeusjackpotjamboree.util.log
import java.lang.Exception

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set
    lateinit var fontTTFManager   : FontTTFManager    private set

    val spriteUtil  by lazy { SpriteUtil() }
    val musicUtil   by lazy { MusicUtil() }
    val soundUtil   by lazy { SoundUtil() }
    val fontTTFUtil by lazy { FontTTFUtil() }
    val themeUtil   by lazy { ThemeUtil(spriteUtil) }

    var backgroundColor = GameColor.background

    override fun create() {
        assetManager      = AssetManager()
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
        } catch (e: Exception) { log("exception: ${e.message}") }
    }

}