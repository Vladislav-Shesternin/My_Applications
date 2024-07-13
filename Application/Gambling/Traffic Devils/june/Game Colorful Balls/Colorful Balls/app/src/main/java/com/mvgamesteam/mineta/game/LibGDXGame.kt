package com.mvgamesteam.mineta.game

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.mvgamesteam.mineta.GameActivity
import com.mvgamesteam.mineta.game.manager.*
import com.mvgamesteam.mineta.game.manager.util.MusicUtil
import com.mvgamesteam.mineta.game.manager.util.SoundUtil
import com.mvgamesteam.mineta.game.manager.util.SpriteUtil
import com.mvgamesteam.mineta.game.screens.LoadingScreen
import com.mvgamesteam.mineta.game.utils.GColor
import com.mvgamesteam.mineta.game.utils.advanced.AdvancedGame
import com.mvgamesteam.mineta.game.utils.disposeAll
import com.mvgamesteam.mineta.util.log

class LibGDXGame(val activity: GameActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val Sap by lazy { SpriteUtil.Sap() }
    val Jer by lazy { SpriteUtil.Jer() }

    val musicUtil by lazy { MusicUtil()    }
    val soundUtil by lazy { SoundUtil()    }

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

    val prefsDialog: SharedPreferences = activity.getSharedPreferences("Voiletta", MODE_PRIVATE)

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