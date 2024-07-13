package com.kongregate.mobile.burrit.game

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import com.kongregate.mobile.burrit.GameActivity
import com.kongregate.mobile.burrit.game.manager.*
import com.kongregate.mobile.burrit.game.manager.util.MusicUtil
import com.kongregate.mobile.burrit.game.manager.util.SoundUtil
import com.kongregate.mobile.burrit.game.manager.util.SpriteUtil
import com.kongregate.mobile.burrit.game.screens.LoadingScreen
import com.kongregate.mobile.burrit.game.utils.GColor
import com.kongregate.mobile.burrit.game.utils.advanced.AdvancedGame
import com.kongregate.mobile.burrit.game.utils.disposeAll
import com.kongregate.mobile.burrit.util.log

class LibGDXGame(val activity: GameActivity) : AdvancedGame() {

    lateinit var assetManager     : AssetManager      private set
    lateinit var navigationManager: NavigationManager private set
    lateinit var spriteManager    : SpriteManager     private set
    lateinit var musicManager     : MusicManager      private set
    lateinit var soundManager     : SoundManager      private set

    val lobster by lazy { SpriteUtil.Lobster() }
    val alpha   by lazy { SpriteUtil.Alpha()   }

    val musicUtil by lazy { MusicUtil() }
    val soundUtil by lazy { SoundUtil() }

    var backgroundColor = GColor.bckg
    val disposableSet   = mutableSetOf<Disposable>()

    override fun create() {
        navigationManager = NavigationManager(this)
        assetManager      = AssetManager()
        spriteManager     = SpriteManager(assetManager)

        musicManager      = MusicManager(assetManager)
        soundManager      = SoundManager(assetManager)

        navigationManager.navigate(LoadingScreen::class.java.name)
    }

    val prefsDialog: SharedPreferences = activity.getSharedPreferences("Atom", MODE_PRIVATE)

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