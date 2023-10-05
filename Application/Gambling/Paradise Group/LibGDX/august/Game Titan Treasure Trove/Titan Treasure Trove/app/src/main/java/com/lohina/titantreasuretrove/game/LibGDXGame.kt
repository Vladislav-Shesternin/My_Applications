package com.lohina.titantreasuretrove.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import com.lohina.titantreasuretrove.MainActivity
import com.lohina.titantreasuretrove.game.manager.FontTTFManager
import com.lohina.titantreasuretrove.game.manager.MusicManager
import com.lohina.titantreasuretrove.game.manager.NavigationManager
import com.lohina.titantreasuretrove.game.manager.SoundManager
import com.lohina.titantreasuretrove.game.manager.SpriteManager
import com.lohina.titantreasuretrove.game.manager.util.FontTTFUtil
import com.lohina.titantreasuretrove.game.manager.util.MusicUtil
import com.lohina.titantreasuretrove.game.manager.util.SoundUtil
import com.lohina.titantreasuretrove.game.manager.util.SpriteUtil
import com.lohina.titantreasuretrove.game.screens.SplashScreen
import com.lohina.titantreasuretrove.game.utils.GameColor
import com.lohina.titantreasuretrove.game.utils.advanced.AdvancedGame
import com.lohina.titantreasuretrove.util.log

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
//    val themeUtil   by lazy { ThemeUtil(spriteUtil) }

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