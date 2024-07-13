package com.duckduckmoosedesign.cpk.game

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.duckduckmoosedesign.cpk.GameActivity
import com.duckduckmoosedesign.cpk.game.manager.*
import com.duckduckmoosedesign.cpk.game.manager.util.MusicUtil
import com.duckduckmoosedesign.cpk.game.manager.util.SoundUtil
import com.duckduckmoosedesign.cpk.game.manager.util.SpriteUtil
import com.duckduckmoosedesign.cpk.game.screens.FirsterScreen
import com.duckduckmoosedesign.cpk.game.utils.GColor
import com.duckduckmoosedesign.cpk.game.utils.advanced.AdvancedGame
import com.duckduckmoosedesign.cpk.game.utils.disposeAll
import com.duckduckmoosedesign.cpk.util.log

class LibGDXGame(val activity: GameActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val Mis by lazy { SpriteUtil.Gelexy() }
    val Ser by lazy { SpriteUtil.Sarafanna() }

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

        navigationManager.navigate(FirsterScreen::class.java.name)
    }

    val prefsDialog: SharedPreferences = activity.getSharedPreferences("Ebbarek", MODE_PRIVATE)

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